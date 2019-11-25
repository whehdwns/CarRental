package com.example.corn.carrental.adapter;

public class locationModel {
    int id;
    String address;
    String state;
    String carsavail;
    String phonenumber;

    public locationModel(int id, String address, String state, String carsavail, String phonenumber) {
        this.id = id;
        this.address = address;
        this.state = state;
        this.phonenumber = phonenumber;
        this.carsavail = carsavail;
    }
    public locationModel(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCarsavail() {
        return carsavail;
    }

    public void setCarsavail(String carsavail) {
        this.carsavail = carsavail;
    }


    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }


}
