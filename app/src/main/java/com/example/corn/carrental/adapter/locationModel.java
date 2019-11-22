package com.example.corn.carrental.adapter;

public class locationModel {
    int id, carsavail;
    String address, state, phonenumber;

    public locationModel(int id, String address, String state, String phonenumber, int carsavail) {
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getCarsavail() {
        return carsavail;
    }

    public void setCarsavail(int carsavail) {
        this.carsavail = carsavail;
    }
}
