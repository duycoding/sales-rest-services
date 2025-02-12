package com.duycoding.SaleApplication.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "invoicesIN")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private Buyer buyer;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItem> invoiceItems;

    private Double totalAmount;

    private LocalDateTime createdAt;

    public Invoice() {}

    public Invoice(Long id, Buyer buyer, List<InvoiceItem> invoiceItems, Double totalAmount, LocalDateTime createdAt) {
        this.id = id;
        this.buyer = buyer;
        this.invoiceItems = invoiceItems;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        updateTotalAmount();
    }

    public void updateTotalAmount() {
        if (invoiceItems != null && !invoiceItems.isEmpty()) {
            totalAmount = invoiceItems.stream()
                    .mapToDouble(item -> item.getQuantity() * item.getPriceAtPurchase())
                    .sum();
        } else {
            totalAmount = 0.0;
        }
    }
}
