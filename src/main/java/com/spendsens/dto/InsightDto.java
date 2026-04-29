package com.spendsens.dto;

public class InsightDto {
    private String id;
    private String title;
    private String description;
    private String type;
    private Double value;
    private Double percentage;
    private String category;

    public InsightDto() {}

    public InsightDto(String id, String title, String description, String type,
                      Double value, Double percentage, String category) {
        this.id = id; this.title = title; this.description = description;
        this.type = type; this.value = value; this.percentage = percentage;
        this.category = category;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public Double getValue() { return value; }
    public Double getPercentage() { return percentage; }
    public String getCategory() { return category; }

    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setType(String type) { this.type = type; }
    public void setValue(Double value) { this.value = value; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }
    public void setCategory(String category) { this.category = category; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String id, title, description, type, category;
        private Double value, percentage;

        public Builder id(String v) { this.id = v; return this; }
        public Builder title(String v) { this.title = v; return this; }
        public Builder description(String v) { this.description = v; return this; }
        public Builder type(String v) { this.type = v; return this; }
        public Builder value(Double v) { this.value = v; return this; }
        public Builder percentage(Double v) { this.percentage = v; return this; }
        public Builder category(String v) { this.category = v; return this; }

        public InsightDto build() {
            return new InsightDto(id, title, description, type, value, percentage, category);
        }
    }
}
