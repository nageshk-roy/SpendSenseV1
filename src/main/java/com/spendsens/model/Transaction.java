package com.spendsens.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions", indexes = {
    @Index(columnList = "userId,timestamp"),
    @Index(columnList = "category"),
    @Index(columnList = "dedup_key", unique = true)
})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    private TransactionCategory category;

    @Column(nullable = false)
    private String merchant;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionSource source;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    private String accountLast4;
    private String referenceId;

    @Column(name = "dedup_key", unique = true)
    private String dedupKey;

    @Column(length = 1000)
    private String rawText;

    @Column(nullable = false)
    private Boolean isVerified = false;

    /*@CreationTimestamp
    private LocalDateTime createdAt;*/

    private Long localId;

    public Transaction() {}

    // Getters
    public Long getId() { return id; }
    public User getUser() { return user; }
    public Double getAmount() { return amount; }
    public TransactionType getType() { return type; }
    public TransactionCategory getCategory() { return category; }
    public String getMerchant() { return merchant; }
    public String getDescription() { return description; }
    public TransactionSource getSource() { return source; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getAccountLast4() { return accountLast4; }
    public String getReferenceId() { return referenceId; }
    public String getDedupKey() { return dedupKey; }
    public String getRawText() { return rawText; }
    public Boolean getIsVerified() { return isVerified; }
   /* public LocalDateTime getCreatedAt() { return createdAt; }*/
    public Long getLocalId() { return localId; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setType(TransactionType type) { this.type = type; }
    public void setCategory(TransactionCategory category) { this.category = category; }
    public void setMerchant(String merchant) { this.merchant = merchant; }
    public void setDescription(String description) { this.description = description; }
    public void setSource(TransactionSource source) { this.source = source; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public void setAccountLast4(String accountLast4) { this.accountLast4 = accountLast4; }
    public void setReferenceId(String referenceId) { this.referenceId = referenceId; }
    public void setDedupKey(String dedupKey) { this.dedupKey = dedupKey; }
    public void setRawText(String rawText) { this.rawText = rawText; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }
    public void setLocalId(Long localId) { this.localId = localId; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private User user;
        private Double amount;
        private TransactionType type;
        private TransactionCategory category;
        private String merchant;
        private String description;
        private TransactionSource source;
        private LocalDateTime timestamp;
        private String accountLast4;
        private String referenceId;
        private String dedupKey;
        private String rawText;
        private Boolean isVerified = false;
        private Long localId;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder amount(Double amount) { this.amount = amount; return this; }
        public Builder type(TransactionType type) { this.type = type; return this; }
        public Builder category(TransactionCategory category) { this.category = category; return this; }
        public Builder merchant(String merchant) { this.merchant = merchant; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder source(TransactionSource source) { this.source = source; return this; }
        public Builder timestamp(LocalDateTime timestamp) { this.timestamp = timestamp; return this; }
        public Builder accountLast4(String accountLast4) { this.accountLast4 = accountLast4; return this; }
        public Builder referenceId(String referenceId) { this.referenceId = referenceId; return this; }
        public Builder dedupKey(String dedupKey) { this.dedupKey = dedupKey; return this; }
        public Builder rawText(String rawText) { this.rawText = rawText; return this; }
        public Builder isVerified(Boolean isVerified) { this.isVerified = isVerified; return this; }
        public Builder localId(Long localId) { this.localId = localId; return this; }

        public Transaction build() {
            Transaction t = new Transaction();
            t.id = this.id; t.user = this.user; t.amount = this.amount;
            t.type = this.type; t.category = this.category; t.merchant = this.merchant;
            t.description = this.description; t.source = this.source; t.timestamp = this.timestamp;
            t.accountLast4 = this.accountLast4; t.referenceId = this.referenceId;
            t.dedupKey = this.dedupKey; t.rawText = this.rawText;
            t.isVerified = this.isVerified; t.localId = this.localId;
            return t;
        }
    }
}
