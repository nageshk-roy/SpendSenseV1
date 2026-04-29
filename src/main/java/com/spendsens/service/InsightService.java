package com.spendsens.service;

import com.spendsens.dto.InsightDto;
import com.spendsens.model.*;
import com.spendsens.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InsightService {

    private final TransactionRepository transactionRepository;
    private final BudgetRepository budgetRepository;

    public InsightService(TransactionRepository transactionRepository, BudgetRepository budgetRepository) {
        this.transactionRepository = transactionRepository;
        this.budgetRepository = budgetRepository;
    }

    public List<InsightDto> generateInsights(Long userId, int month, int year) {
        List<InsightDto> insights = new ArrayList<>();

        LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime end = start.plusMonths(1).minusSeconds(1);
        List<Transaction> transactions = transactionRepository.findByUserIdAndTimestampBetween(userId, start, end);
        List<Budget> budgets = budgetRepository.findByUserIdAndMonthAndYear(userId, month, year);

        // Budget exceeded warnings
        for (Budget b : budgets) {
            if (b.getCurrentSpent() > b.getMonthlyLimit()) {
                double overage = b.getCurrentSpent() - b.getMonthlyLimit();
                insights.add(InsightDto.builder()
                    .id("budget_" + b.getCategory().name())
                    .title("Budget Exceeded: " + b.getCategory().name())
                    .description(String.format("You've exceeded your %s budget by ₹%.0f", b.getCategory().name(), overage))
                    .type("WARNING").value(overage).category(b.getCategory().name())
                    .build());
            }
        }

        // Top spending category
        Map<TransactionCategory, Double> categorySpend = transactions.stream()
            .filter(t -> t.getType() == TransactionType.DEBIT)
            .collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));

        categorySpend.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .ifPresent(entry -> insights.add(InsightDto.builder()
                .id("top_category")
                .title("Top Spending: " + entry.getKey().name())
                .description(String.format("You spent ₹%.0f on %s this month", entry.getValue(), entry.getKey().name()))
                .type("INFO").value(entry.getValue()).category(entry.getKey().name())
                .build()));

        // Savings rate
        double income = transactions.stream()
            .filter(t -> t.getType() == TransactionType.CREDIT)
            .mapToDouble(Transaction::getAmount).sum();
        double expense = transactions.stream()
            .filter(t -> t.getType() == TransactionType.DEBIT)
            .mapToDouble(Transaction::getAmount).sum();

        if (income > 0) {
            double rate = (income - expense) / income * 100;
            if (rate > 20) {
                insights.add(InsightDto.builder()
                    .id("savings_good").title("Great Savings Rate!")
                    .description(String.format("You're saving %.1f%% of income. Excellent!", rate))
                    .type("POSITIVE").percentage(rate).build());
            } else if (rate < 10) {
                insights.add(InsightDto.builder()
                    .id("savings_low").title("Low Savings Alert")
                    .description(String.format("Saving only %.1f%% of income. Try reducing non-essentials.", rate))
                    .type("SUGGESTION").percentage(rate).build());
            }
        }

        return insights;
    }
}
