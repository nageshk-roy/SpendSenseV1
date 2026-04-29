package com.spendsens.dto;

import java.time.LocalDateTime;

public class TransactionDto {
    private Long id;
    private Double amount;
    private String type;
    private String category;
    private String merchant;
    private String description;
    private String source;
    private LocalDateTime timestamp;
    private String accountLast4;
    private String referenceId;
    private Boolean isVerified;
    private Long localId;

    public TransactionDto() {}

    public Long getId() { return id; }
    public Double getAmount() { return amount; }
    public String getType() { return type; }
    public String getCategory() { return category; }
    public String getMerchant() { return merchant; }
    public String getDescription() { return description; }
    public String getSource() { return source; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getAccountLast4() { return accountLast4; }
    public String getReferenceId() { return referenceId; }
    public Boolean getIsVerified() { return isVerified; }
    public Long getLocalId() { return localId; }

    public void setId(Long id) { this.id = id; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setType(String type) { this.type = type; }
    public void setCategory(String category) { this.category = category; }
    public void setMerchant(String merchant) { this.merchant = merchant; }
    public void setDescription(String description) { this.description = description; }
    public void setSource(String source) { this.source = source; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public void setAccountLast4(String accountLast4) { this.accountLast4 = accountLast4; }
    public void setReferenceId(String referenceId) { this.referenceId = referenceId; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }
    public void setLocalId(Long localId) { this.localId = localId; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id, localId;
        private Double amount;
        private String type, category, merchant, description, source, accountLast4, referenceId;
        private LocalDateTime timestamp;
        private Boolean isVerified;

        public Builder id(Long v) { this.id = v; return this; }
        public Builder amount(Double v) { this.amount = v; return this; }
        public Builder type(String v) { this.type = v; return this; }
        public Builder category(String v) { this.category = v; return this; }
        public Builder merchant(String v) { this.merchant = v; return this; }
        public Builder description(String v) { this.description = v; return this; }
        public Builder source(String v) { this.source = v; return this; }
        public Builder timestamp(LocalDateTime v) { this.timestamp = v; return this; }
        public Builder accountLast4(String v) { this.accountLast4 = v; return this; }
        public Builder referenceId(String v) { this.referenceId = v; return this; }
        public Builder isVerified(Boolean v) { this.isVerified = v; return this; }
        public Builder localId(Long v) { this.localId = v; return this; }

        public TransactionDto build() {
            TransactionDto d = new TransactionDto();
            d.id = id; d.amount = amount; d.type = type; d.category = category;
            d.merchant = merchant; d.description = description; d.source = source;
            d.timestamp = timestamp; d.accountLast4 = accountLast4; d.referenceId = referenceId;
            d.isVerified = isVerified; d.localId = localId;
            return d;
        }
    }
}
