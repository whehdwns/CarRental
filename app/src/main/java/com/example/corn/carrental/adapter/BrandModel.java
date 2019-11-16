package com.example.corn.carrental.adapter;

public class BrandModel {
    int id;
    String brand, model, type, productionyear, price, color, mile;


    public BrandModel(int id, String brand, String model, String type, String productionyear, String price, String color, String mile) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.productionyear = productionyear;
        this.price = price;
        this.color = color;
        this.mile = mile;
    }
    public BrandModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductionyear() {
        return productionyear;
    }

    public void setProductionyear(String productionyear) {
        this.productionyear = productionyear;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getMile() {
        return mile;
    }

    public void setMile(String mile) {
        this.mile = mile;
    }
}
