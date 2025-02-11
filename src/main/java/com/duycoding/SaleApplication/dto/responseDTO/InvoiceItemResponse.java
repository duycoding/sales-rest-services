package com.duycoding.SaleApplication.dto.responseDTO;

public class InvoiceItemResponse {
    private String goodsName;
    private int quantity;
    private double priceAtPurchase;
    private double totalPrice;

    public InvoiceItemResponse(String goodsName, int quantity, double priceAtPurchase, double totalPrice) {
        this.goodsName = goodsName;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
        this.totalPrice = totalPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
