package com.example.www_lab_w2.requests;

public class OrderDetailRequest {
    private int quantity;
    private long pId;

    public OrderDetailRequest() {
    }

    public OrderDetailRequest(int quantity, long pId) {
        this.quantity = quantity;
        this.pId = pId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }
}
