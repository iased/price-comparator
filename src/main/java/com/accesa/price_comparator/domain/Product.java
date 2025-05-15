package com.accesa.price_comparator.domain;

import com.opencsv.bean.CsvBindByName;

public class Product {
    @CsvBindByName(column = "product_id")
    private String id;
    @CsvBindByName(column = "product_name")
    private String name;
    @CsvBindByName(column = "product_category")
    private String category;
    @CsvBindByName(column = "brand")
    private String brand;
    @CsvBindByName(column = "product_quantity")
    private double quantity;
    @CsvBindByName(column = "product_unit")
    private String unit;
    @CsvBindByName(column = "price")
    private double price;
    @CsvBindByName(column = "currency")
    private String currency;

    public Product(String id, String name, String category, String brand, double quantity, String unit, double price, String currency)
    {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.currency = currency;
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
}



