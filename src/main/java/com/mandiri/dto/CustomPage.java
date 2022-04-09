package com.mandiri.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class CustomPage<T> {
    private final List<T> content;
    private final Integer pageNumber;
    private final Integer pageSize;
    private final Integer totalPages;
    private final Long totalElement;

    public CustomPage(Page<T> pageData) {
        this.content = pageData.getContent();
        this.pageNumber = pageData.getPageable().getPageNumber();
        this.pageSize = pageData.getPageable().getPageSize();
        this.totalPages = pageData.getTotalPages();
        this.totalElement = pageData.getTotalElements();
    }

    public List<T> getContent() {
        return content;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Long getTotalElement() {
        return totalElement;
    }
}

