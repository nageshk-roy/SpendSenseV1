package com.spendsens.dto;

public class BudgetRequest {
    private String category;
    private Double monthlyLimit;
    private Integer month;
    private Integer year;

    public BudgetRequest() {}

    public String getCategory() { return category; }
    public Double getMonthlyLimit() { return monthlyLimit; }
    public Integer getMonth() { return month; }
    public Integer getYear() { return year; }

    public void setCategory(String category) { this.category = category; }
    public void setMonthlyLimit(Double monthlyLimit) { this.monthlyLimit = monthlyLimit; }
    public void setMonth(Integer month) { this.month = month; }
    public void setYear(Integer year) { this.year = year; }
}
