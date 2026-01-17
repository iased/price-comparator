package com.uo.price_comparator.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OfferDto {
    private String store;
    private BigDecimal price;
    private BigDecimal pricePerUnit;
    private LocalDate date;
    private Integer discountPercent;
    private BigDecimal discountedPrice;
    private BigDecimal discountedPricePerUnit;

    public String getStore() { return store; }
    public void setStore(String store) { this.store = store; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public BigDecimal getPricePerUnit() { return pricePerUnit; }
    public void setPricePerUnit(BigDecimal pricePerUnit) { this.pricePerUnit = pricePerUnit; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Integer getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(Integer discountPercent) { this.discountPercent = discountPercent; }

    public BigDecimal getDiscountedPricePerUnit() { return discountedPricePerUnit; }
    public void setDiscountedPricePerUnit(BigDecimal discountedPricePerUnit) { this.discountedPricePerUnit = discountedPricePerUnit; }

    public BigDecimal getDiscountedPrice() { return discountedPrice; }
    public void setDiscountedPrice(BigDecimal discountedPrice) { this.discountedPrice = discountedPrice; }
}
