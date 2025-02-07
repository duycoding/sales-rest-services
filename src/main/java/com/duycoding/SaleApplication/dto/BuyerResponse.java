package com.duycoding.SaleApplication.dto;

public class BuyerResponse {
    private String name;

    public BuyerResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
