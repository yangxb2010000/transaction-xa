package com.example.xb.demo.tx_xa.service;

public class CreateOrderRequest {
    private int productId;
    private int userId;
    private int count;

    public int getProductId() {
        return productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
