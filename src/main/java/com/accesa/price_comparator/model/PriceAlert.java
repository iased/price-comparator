package com.accesa.price_comparator.model;

import java.util.UUID;

public class PriceAlert {
    private final UUID id;
    private String productId;
    private String store;
    private double targetPrice;
    private double discountedPrice;
    private boolean triggered;

    public PriceAlert(){
        this.id = UUID.randomUUID();
        triggered = false;
    }

    public PriceAlert(String productId, String store, double targetPrice, double discountedPrice) {
        this();
        this.productId = productId;
        this.store = store;
        this.targetPrice = targetPrice;
        this.discountedPrice = discountedPrice;
    }

    public UUID getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getStore() {
        return store;
    }

    public double getTargetPrice() {
        return targetPrice;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setTargetPrice(double targetPrice) {
        this.targetPrice = targetPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }
}
