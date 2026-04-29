package com.spendsens.dto;

import java.util.Map;

public class DashboardSummaryDto {
    private Double totalBalance;
    private Double monthlyIncome;
    private Double monthlyExpense;
    private Double savingsRate;
    private Map<String, Double> categoryBreakdown;
    private InsightDto topInsight;

    public DashboardSummaryDto() {}

    public DashboardSummaryDto(Double totalBalance, Double monthlyIncome, Double monthlyExpense,
                                Double savingsRate, Map<String, Double> categoryBreakdown, InsightDto topInsight) {
        this.totalBalance = totalBalance;
        this.monthlyIncome = monthlyIncome;
        this.monthlyExpense = monthlyExpense;
        this.savingsRate = savingsRate;
        this.categoryBreakdown = categoryBreakdown;
        this.topInsight = topInsight;
    }

    public Double getTotalBalance() { return totalBalance; }
    public Double getMonthlyIncome() { return monthlyIncome; }
    public Double getMonthlyExpense() { return monthlyExpense; }
    public Double getSavingsRate() { return savingsRate; }
    public Map<String, Double> getCategoryBreakdown() { return categoryBreakdown; }
    public InsightDto getTopInsight() { return topInsight; }

    public void setTotalBalance(Double totalBalance) { this.totalBalance = totalBalance; }
    public void setMonthlyIncome(Double monthlyIncome) { this.monthlyIncome = monthlyIncome; }
    public void setMonthlyExpense(Double monthlyExpense) { this.monthlyExpense = monthlyExpense; }
    public void setSavingsRate(Double savingsRate) { this.savingsRate = savingsRate; }
    public void setCategoryBreakdown(Map<String, Double> categoryBreakdown) { this.categoryBreakdown = categoryBreakdown; }
    public void setTopInsight(InsightDto topInsight) { this.topInsight = topInsight; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Double totalBalance, monthlyIncome, monthlyExpense, savingsRate;
        private Map<String, Double> categoryBreakdown;
        private InsightDto topInsight;

        public Builder totalBalance(Double v) { this.totalBalance = v; return this; }
        public Builder monthlyIncome(Double v) { this.monthlyIncome = v; return this; }
        public Builder monthlyExpense(Double v) { this.monthlyExpense = v; return this; }
        public Builder savingsRate(Double v) { this.savingsRate = v; return this; }
        public Builder categoryBreakdown(Map<String, Double> v) { this.categoryBreakdown = v; return this; }
        public Builder topInsight(InsightDto v) { this.topInsight = v; return this; }

        public DashboardSummaryDto build() {
            return new DashboardSummaryDto(totalBalance, monthlyIncome, monthlyExpense,
                    savingsRate, categoryBreakdown, topInsight);
        }
    }
}
