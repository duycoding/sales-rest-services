package com.duycoding.SaleApplication.Exception.CustomException;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String goodsName) {
        super("Not enough stock for goods: " + goodsName);
    }
}
