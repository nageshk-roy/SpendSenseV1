package com.spendsens.service;

import com.spendsens.dto.*;
import com.spendsens.model.*;
import com.spendsens.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;

    public BudgetService(BudgetRepository budgetRepository, UserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
    }

    public List<BudgetDto> getBudgets(Long userId, int month, int year) {
        return budgetRepository.findByUserIdAndMonthAndYear(userId, month, year)
            .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional
    public BudgetDto createOrUpdateBudget(Long userId, BudgetRequest req) {
        TransactionCategory category = TransactionCategory.valueOf(req.getCategory());
        Budget budget = budgetRepository
            .findByUserIdAndCategoryAndMonthAndYear(userId, category, req.getMonth(), req.getYear())
            .orElse(Budget.builder()
                .user(userRepository.getReferenceById(userId))
                .category(category)
                .month(req.getMonth())
                .year(req.getYear())
                .build());

        budget.setMonthlyLimit(req.getMonthlyLimit());
        return toDto(budgetRepository.save(budget));
    }

    @Transactional
    public void deleteBudget(Long userId, Long budgetId) {
        Budget budget = budgetRepository.findById(budgetId)
            .orElseThrow(() -> new RuntimeException("Budget not found"));
        if (!budget.getUser().getId().equals(userId))
            throw new RuntimeException("Unauthorized");
        budgetRepository.delete(budget);
    }

    private BudgetDto toDto(Budget b) {
        return BudgetDto.builder()
            .id(b.getId()).category(b.getCategory().name())
            .monthlyLimit(b.getMonthlyLimit()).currentSpent(b.getCurrentSpent())
            .month(b.getMonth()).year(b.getYear())
            .build();
    }
}
