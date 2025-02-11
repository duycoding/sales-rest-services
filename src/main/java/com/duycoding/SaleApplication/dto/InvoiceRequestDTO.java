package com.duycoding.SaleApplication.dto;

import java.util.List;

public class InvoiceRequestDTO {
    private Long buyerId;
    private List<InvoiceItemDTO> items; // Strongly-typed list

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public List<InvoiceItemDTO> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItemDTO> items) {
        this.items = items;
    }
}
