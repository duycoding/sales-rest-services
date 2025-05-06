package com.duycoding.SaleApplication.Exception.CustomException;

public class GoodsNotFoundException extends RuntimeException {
    public GoodsNotFoundException(Long goodsId) {
        super("Goods not found with ID: " + goodsId);
    }
}