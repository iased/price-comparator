package com.uo.price_comparator.dto;

import java.time.LocalDate;

public class OfferDto {
    private String store;
    private double price;
    private double pricePerUnit;
    private LocalDate date;
    private Integer discountPercent;
    private Double discountedPrice;
    private Double discountedPricePerUnit;

    public String getStore() { return store; }
    public void setStore(String store) { this.store = store; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getPricePerUnit() { return pricePerUnit; }
    public void setPricePerUnit(double pricePerUnit) { this.pricePerUnit = pricePerUnit; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Integer getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(Integer discountPercent) { this.discountPercent = discountPercent; }

    public Double getDiscountedPricePerUnit() { return discountedPricePerUnit; }
    public void setDiscountedPricePerUnit(Double discountedPricePerUnit) { this.discountedPricePerUnit = discountedPricePerUnit; }

    public Double getDiscountedPrice() { return discountedPrice; }
    public void setDiscountedPrice(Double discountedPrice) { this.discountedPrice = discountedPrice; }
}
