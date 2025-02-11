package com.duycoding.SaleApplication.dto.responseDTO;

import java.time.LocalDateTime;
import java.util.List;

public class InvoiceResponse {
    private Long id;
    private String buyerName;
    private List<InvoiceItemResponse> items;
    private Double totalAmount;
    private LocalDateTime createdAt;

    public InvoiceResponse(Long id, String buyerName, List<InvoiceItemResponse> items, Double totalAmount, LocalDateTime createdAt) {
        this.id = id;
        this.buyerName = buyerName;
        this.items = items;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public List<InvoiceItemResponse> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItemResponse> items) {
        this.items = items;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
