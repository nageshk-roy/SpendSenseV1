package com.spendsens.model;

import jakarta.persistence.*;

@Entity
@Table(name = "budgets", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"userId", "category", "month", "year"})
})
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionCategory category;

    @Column(nullable = false)
    private Double monthlyLimit;

    @Column(nullable = false)
    private Double currentSpent = 0.0;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private Integer year;

    public Budget() {}

    // Getters
    public Long getId() { return id; }
    public User getUser() { return user; }
    public TransactionCategory getCategory() { return category; }
    public Double getMonthlyLimit() { return monthlyLimit; }
    public Double getCurrentSpent() { return currentSpent; }
    public Integer getMonth() { return month; }
    public Integer getYear() { return year; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setCategory(TransactionCategory category) { this.category = category; }
    public void setMonthlyLimit(Double monthlyLimit) { this.monthlyLimit = monthlyLimit; }
    public void setCurrentSpent(Double currentSpent) { this.currentSpent = currentSpent; }
    public void setMonth(Integer month) { this.month = month; }
    public void setYear(Integer year) { this.year = year; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private User user;
        private TransactionCategory category;
        private Double monthlyLimit;
        private Double currentSpent = 0.0;
        private Integer month;
        private Integer year;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder category(TransactionCategory category) { this.category = category; return this; }
        public Builder monthlyLimit(Double monthlyLimit) { this.monthlyLimit = monthlyLimit; return this; }
        public Builder currentSpent(Double currentSpent) { this.currentSpent = currentSpent; return this; }
        public Builder month(Integer month) { this.month = month; return this; }
        public Builder year(Integer year) { this.year = year; return this; }

        public Budget build() {
            Budget b = new Budget();
            b.id = this.id; b.user = this.user; b.category = this.category;
            b.monthlyLimit = this.monthlyLimit; b.currentSpent = this.currentSpent;
            b.month = this.month; b.year = this.year;
            return b;
        }
    }
}
