package com.example.www_lab_w2.enums;

public enum ProductStatus {
    ACTIVE(1),
    INACTIVE(0),
    TERMINATED(-1);

    private int value;

    ProductStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}