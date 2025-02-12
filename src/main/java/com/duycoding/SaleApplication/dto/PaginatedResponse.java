package com.duycoding.SaleApplication.dto;

import java.util.List;

//public class PaginatedResponse<T> {
//    private int currentPage;
//    private int totalPages;
//    private long totalItems;
//    private List<T> sellers;
//
//    public PaginatedResponse(int currentPage, int totalPages, long totalItems, List<T> sellers) {
//        this.currentPage = currentPage;
//        this.totalPages = totalPages;
//        this.totalItems = totalItems;
//        this.sellers = sellers;
//    }
//
//    public int getCurrentPage() {
//        return currentPage;
//    }
//
//    public void setCurrentPage(int currentPage) {
//        this.currentPage = currentPage;
//    }
//
//    public int getTotalPages() {
//        return totalPages;
//    }
//
//    public void setTotalPages(int totalPages) {
//        this.totalPages = totalPages;
//    }
//
//    public long getTotalItems() {
//        return totalItems;
//    }
//
//    public void setTotalItems(long totalItems) {
//        this.totalItems = totalItems;
//    }
//
//    public List<T> getSellers() {
//        return sellers;
//    }
//
//    public void setSellers(List<T> sellers) {
//        this.sellers = sellers;
//    }
//}

public class PaginatedResponse<T> {
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private List<T> data; // Đổi từ "sellers" thành "data"

    public PaginatedResponse(int currentPage, int totalPages, long totalItems, List<T> data) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
        this.data = data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public List<T> getData() {
        return data;
    }
}

