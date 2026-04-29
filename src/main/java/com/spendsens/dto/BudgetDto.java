package com.spendsens.dto;

public class BudgetDto {
    private Long id;
    private String category;
    private Double monthlyLimit;
    private Double currentSpent;
    private Integer month;
    private Integer year;

    public BudgetDto() {}

    public BudgetDto(Long id, String category, Double monthlyLimit, Double currentSpent, Integer month, Integer year) {
        this.id = id; this.category = category; this.monthlyLimit = monthlyLimit;
        this.currentSpent = currentSpent; this.month = month; this.year = year;
    }

    public Long getId() { return id; }
    public String getCategory() { return category; }
    public Double getMonthlyLimit() { return monthlyLimit; }
    public Double getCurrentSpent() { return currentSpent; }
    public Integer getMonth() { return month; }
    public Integer getYear() { return year; }

    public void setId(Long id) { this.id = id; }
    public void setCategory(String category) { this.category = category; }
    public void setMonthlyLimit(Double monthlyLimit) { this.monthlyLimit = monthlyLimit; }
    public void setCurrentSpent(Double currentSpent) { this.currentSpent = currentSpent; }
    public void setMonth(Integer month) { this.month = month; }
    public void setYear(Integer year) { this.year = year; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String category;
        private Double monthlyLimit, currentSpent;
        private Integer month, year;

        public Builder id(Long v) { this.id = v; return this; }
        public Builder category(String v) { this.category = v; return this; }
        public Builder monthlyLimit(Double v) { this.monthlyLimit = v; return this; }
        public Builder currentSpent(Double v) { this.currentSpent = v; return this; }
        public Builder month(Integer v) { this.month = v; return this; }
        public Builder year(Integer v) { this.year = v; return this; }

        public BudgetDto build() {
            return new BudgetDto(id, category, monthlyLimit, currentSpent, month, year);
        }
    }
}
