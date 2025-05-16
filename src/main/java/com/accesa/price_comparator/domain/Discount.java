package com.accesa.price_comparator.domain;

import com.opencsv.bean.CsvBindByName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Discount {
    @CsvBindByName(column = "product_id")
    private String id;
    @CsvBindByName(column = "product_name")
    private String name;
    @CsvBindByName(column = "brand")
    private String brand;
    @CsvBindByName(column = "package_quantity")
    private double quantity;
    @CsvBindByName(column = "package_unit")
    private String unit;
    @CsvBindByName(column = "product_category")
    private String category;
    @CsvBindByName(column = "from_date")
    private String fromDate;
    @CsvBindByName(column = "to_date")
    private String toDate;
    @CsvBindByName(column = "percentage_of_discount")
    private int discount;

    private String store;

    public Discount() {

    }

    public Discount(String id, String name, String brand, double quantity, String unit, String category, String fromDate, String toDate, int discount) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.quantity = quantity;
        this.unit = unit;
        this.category = category;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
