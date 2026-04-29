package com.spendsens.dto;

import java.time.LocalDateTime;

public class TransactionRequest {
    private Double amount;
    private String type;
    private String category;
    private String merchant;
    private String description;
    private String source;
    private LocalDateTime timestamp;
    private String accountLast4;
    private String referenceId;
    private String rawText;
    private Long localId;

    public TransactionRequest() {}

    public Double getAmount() { return amount; }
    public String getType() { return type; }
    public String getCategory() { return category; }
    public String getMerchant() { return merchant; }
    public String getDescription() { return description; }
    public String getSource() { return source; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getAccountLast4() { return accountLast4; }
    public String getReferenceId() { return referenceId; }
    public String getRawText() { return rawText; }
    public Long getLocalId() { return localId; }

    public void setAmount(Double amount) { this.amount = amount; }
    public void setType(String type) { this.type = type; }
    public void setCategory(String category) { this.category = category; }
    public void setMerchant(String merchant) { this.merchant = merchant; }
    public void setDescription(String description) { this.description = description; }
    public void setSource(String source) { this.source = source; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public void setAccountLast4(String accountLast4) { this.accountLast4 = accountLast4; }
    public void setReferenceId(String referenceId) { this.referenceId = referenceId; }
    public void setRawText(String rawText) { this.rawText = rawText; }
    public void setLocalId(Long localId) { this.localId = localId; }
}
