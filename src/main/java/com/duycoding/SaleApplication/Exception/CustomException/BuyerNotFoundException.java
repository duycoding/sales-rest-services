package com.duycoding.SaleApplication.Exception.CustomException;

public class BuyerNotFoundException extends RuntimeException {
    public BuyerNotFoundException(Long buyerId) {
        super("Buyer not found with ID: " + buyerId);
    }
}