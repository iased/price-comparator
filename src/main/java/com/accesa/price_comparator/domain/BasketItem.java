package com.accesa.price_comparator.domain;

public class BasketItem {
    private String productId;
    private int quantity;
    private String brand;

    public BasketItem(String productId, int quantity, String brand) {
        this.productId = productId;
        this.quantity = quantity;
        this.brand = brand;
    }

    public BasketItem() {}

    public BasketItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
