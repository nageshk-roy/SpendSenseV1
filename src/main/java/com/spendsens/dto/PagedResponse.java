package com.spendsens.dto;

import java.util.List;

public class PagedResponse<T> {
    private List<T> content;
    private Long totalElements;
    private Integer totalPages;
    private Integer currentPage;
    private Boolean hasNext;

    public PagedResponse() {}

    public PagedResponse(List<T> content, Long totalElements, Integer totalPages, Integer currentPage, Boolean hasNext) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.hasNext = hasNext;
    }

    public List<T> getContent() { return content; }
    public Long getTotalElements() { return totalElements; }
    public Integer getTotalPages() { return totalPages; }
    public Integer getCurrentPage() { return currentPage; }
    public Boolean getHasNext() { return hasNext; }

    public void setContent(List<T> content) { this.content = content; }
    public void setTotalElements(Long totalElements) { this.totalElements = totalElements; }
    public void setTotalPages(Integer totalPages) { this.totalPages = totalPages; }
    public void setCurrentPage(Integer currentPage) { this.currentPage = currentPage; }
    public void setHasNext(Boolean hasNext) { this.hasNext = hasNext; }

    public static <T> Builder<T> builder() { return new Builder<>(); }

    public static class Builder<T> {
        private List<T> content;
        private Long totalElements;
        private Integer totalPages, currentPage;
        private Boolean hasNext;

        public Builder<T> content(List<T> v) { this.content = v; return this; }
        public Builder<T> totalElements(Long v) { this.totalElements = v; return this; }
        public Builder<T> totalPages(Integer v) { this.totalPages = v; return this; }
        public Builder<T> currentPage(Integer v) { this.currentPage = v; return this; }
        public Builder<T> hasNext(Boolean v) { this.hasNext = v; return this; }

        public PagedResponse<T> build() {
            return new PagedResponse<>(content, totalElements, totalPages, currentPage, hasNext);
        }
    }
}
