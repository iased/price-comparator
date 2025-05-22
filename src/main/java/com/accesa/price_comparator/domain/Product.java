package com.accesa.price_comparator.domain;

import com.opencsv.bean.CsvBindByName;

import java.time.LocalDate;

public class Product {
    @CsvBindByName(column = "product_id")
    private String id;
    @CsvBindByName(column = "product_name")
    private String name;
    @CsvBindByName(column = "product_category")
    private String category;
    @CsvBindByName(column = "brand")
    private String brand;
    @CsvBindByName(column = "package_quantity")
    private double quantity;
    @CsvBindByName(column = "package_unit")
    private String unit;
    @CsvBindByName(column = "price")
    private double price;
    @CsvBindByName(column = "currency")
    private String currency;

    private String store;
    private LocalDate date;

    private double pricePerUnit;

    public Product(){

    }

    public Product(String id, String name, String category, String brand, double quantity, String unit, double price,
                   String currency, String store, LocalDate date, double pricePerUnit)
    {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.currency = currency;
        this.store = store;
        this.date = date;
        this.pricePerUnit = pricePerUnit;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getCategory(){
        return this.category;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getBrand(){
        return this.brand;
    }

    public void setQuantity(double quantity){
        this.quantity = quantity;
    }

    public double getQuantity(){
        return this.quantity;
    }

    public void setUnit(String unit){
        this.unit = unit;
    }

    public String getUnit(){
        return this.unit;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice(){
        return this.price;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }

    public String getCurrency(){
        return this.currency;
    }

    public void setStore(String store){
        this.store = store;
    }

    public String getStore(){
        return this.store;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public double getPricePerUnit() {
        return this.pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    private double switchToStandardUnit(double quantity, String unit) {
        switch (unit) {
            case "ml":
                return quantity / 1000;
            case "l":
                return quantity;
            case "g":
                return quantity / 1000;
            case "kg":
                return quantity;
            default:
                return quantity;
        }
    }

    public void calculatePricePerUnit() {
        this.pricePerUnit = this.price / switchToStandardUnit(this.quantity, this.unit);
    }
}



