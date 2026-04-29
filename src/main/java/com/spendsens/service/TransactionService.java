package com.spendsens.service;

import com.spendsens.dto.*;
import com.spendsens.model.*;
import com.spendsens.repository.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final BudgetRepository budgetRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              UserRepository userRepository,
                              BudgetRepository budgetRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.budgetRepository = budgetRepository;
    }

    public PagedResponse<TransactionDto> getTransactions(Long userId, int page, int size,
            LocalDateTime startDate, LocalDateTime endDate, String category, String type) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Transaction> txnPage;

        if (category != null) {
            txnPage = transactionRepository.findByUserIdAndCategoryOrderByTimestampDesc(
                userId, TransactionCategory.valueOf(category), pageable);
        } else if (startDate != null && endDate != null) {
            txnPage = transactionRepository.findByUserIdAndTimestampBetweenOrderByTimestampDesc(
                userId, startDate, endDate, pageable);
        } else {
            txnPage = transactionRepository.findByUserIdOrderByTimestampDesc(userId, pageable);
        }

        return PagedResponse.<TransactionDto>builder()
            .content(txnPage.getContent().stream().map(this::toDto).collect(Collectors.toList()))
            .totalElements(txnPage.getTotalElements())
            .totalPages(txnPage.getTotalPages())
            .currentPage(page)
            .hasNext(txnPage.hasNext())
            .build();
    }

    @Transactional
    public TransactionDto createTransaction(Long userId, TransactionRequest req) {
        User user = userRepository.getReferenceById(userId);

        String dedupKey = buildDedupKey(req.getAmount(), req.getMerchant(),
            req.getType(), req.getTimestamp());

        if (transactionRepository.existsByDedupKey(dedupKey)) {
            return transactionRepository.findByDedupKey(dedupKey)
                .map(this::toDto).orElseThrow();
        }

        Transaction txn = Transaction.builder()
            .user(user)
            .amount(req.getAmount())
            .type(TransactionType.valueOf(req.getType()))
            .category(req.getCategory() != null ? TransactionCategory.valueOf(req.getCategory()) : TransactionCategory.OTHER)
            .merchant(req.getMerchant() != null ? req.getMerchant() : "Unknown")
            .description(req.getDescription() != null ? req.getDescription() : "")
            .source(TransactionSource.valueOf(req.getSource()))
            .timestamp(req.getTimestamp() != null ? req.getTimestamp() : LocalDateTime.now())
            .accountLast4(req.getAccountLast4())
            .referenceId(req.getReferenceId())
            .rawText(req.getRawText())
            .dedupKey(dedupKey)
            .localId(req.getLocalId())
            .build();

        txn = transactionRepository.save(txn);

        if (txn.getType() == TransactionType.DEBIT) {
            LocalDateTime ts = txn.getTimestamp();
            budgetRepository.incrementSpent(userId, txn.getCategory(),
                txn.getAmount(), ts.getMonthValue(), ts.getYear());
        }

        return toDto(txn);
    }

    @Transactional
    public List<TransactionDto> bulkSync(Long userId, List<TransactionRequest> requests) {
        return requests.stream()
            .map(req -> createTransaction(userId, req))
            .collect(Collectors.toList());
    }

    @Transactional
    public void deleteTransaction(Long userId, Long txnId) {
        Transaction txn = transactionRepository.findById(txnId)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));
        if (!txn.getUser().getId().equals(userId))
            throw new RuntimeException("Unauthorized");
        transactionRepository.delete(txn);
    }

    public DashboardSummaryDto getDashboardSummary(Long userId, int month, int year) {
        Double income = transactionRepository.getMonthlyIncome(userId, year, month);
        Double expense = transactionRepository.getMonthlyExpense(userId, year, month);
        income = income != null ? income : 0.0;
        expense = expense != null ? expense : 0.0;

        List<Object[]> breakdown = transactionRepository.getCategoryBreakdown(userId, year, month);
        Map<String, Double> categoryMap = new LinkedHashMap<>();
        for (Object[] row : breakdown) {
            categoryMap.put(row[0].toString(), ((Number) row[1]).doubleValue());
        }

        double savingsRate = income > 0 ? (income - expense) / income * 100 : 0;

        return DashboardSummaryDto.builder()
            .totalBalance(income - expense)
            .monthlyIncome(income)
            .monthlyExpense(expense)
            .savingsRate(savingsRate)
            .categoryBreakdown(categoryMap)
            .build();
    }

    private String buildDedupKey(Double amount, String merchant, String type, LocalDateTime ts) {
        String merchantNorm = merchant != null ? merchant.toLowerCase().replaceAll("\\s+", "") : "unknown";
        String date = ts != null ? ts.toLocalDate().toString() : LocalDate.now().toString();
        return String.format("%.2f_%s_%s_%s", amount, merchantNorm, type, date);
    }

    private TransactionDto toDto(Transaction t) {
        return TransactionDto.builder()
            .id(t.getId()).amount(t.getAmount())
            .type(t.getType().name())
            .category(t.getCategory() != null ? t.getCategory().name() : "OTHER")
            .merchant(t.getMerchant()).description(t.getDescription())
            .source(t.getSource().name()).timestamp(t.getTimestamp())
            .accountLast4(t.getAccountLast4()).referenceId(t.getReferenceId())
            .isVerified(t.getIsVerified()).localId(t.getLocalId())
            .build();
    }
}
