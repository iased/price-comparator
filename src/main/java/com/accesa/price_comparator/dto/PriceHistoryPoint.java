package com.accesa.price_comparator.dto;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceHistoryPoint)) return false;
        PriceHistoryPoint that = (PriceHistoryPoint) o;
        return Double.compare(that.price, price) == 0 &&
                Double.compare(that.discountedPrice, discountedPrice) == 0 &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(store, that.store) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, store, fromDate, toDate, price, discountedPrice);
    }
}
