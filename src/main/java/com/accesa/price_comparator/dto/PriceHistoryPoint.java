package com.accesa.price_comparator.dto;

import java.time.LocalDate;

public class PriceHistoryPoint {
    private String productId;
    private String store;
    private LocalDate fromDate;
    private LocalDate toDate;
    private double price;
    private double discountedPrice;

    public PriceHistoryPoint(String productId, String store, LocalDate fromDate, LocalDate toDate, double price, double discountedPrice) {
        this.productId = productId;
        this.store = store;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.price = price;
        this.discountedPrice = discountedPrice;
    }

    public String getProductId() {
        return productId;
    }

    public String getStore() {
        return store;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }
}
