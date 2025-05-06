package com.duycoding.SaleApplication.dto;

public class GoodsDTO {
    private Long id;
    private String name;
    private Long sellerId;
    private double price;
    private int stock;
    private String category;
    private boolean isDeleted;

    public GoodsDTO() {}

    public GoodsDTO(Long id, String name, Long sellerId, double price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.sellerId = sellerId;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public GoodsDTO(Long id, String name, Long sellerId, double price, int stock, String category, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.sellerId = sellerId;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.isDeleted = isDeleted;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Long getSellerId() { return sellerId; }

    public void setSellerId(Long sellerId) { this.sellerId = sellerId; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public boolean isDeleted() { return isDeleted; }

    public void setDeleted(boolean deleted) { isDeleted = deleted; }
}
