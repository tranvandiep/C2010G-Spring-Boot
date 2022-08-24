/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gokisoft.SpringBootExample.models;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Tour implements Serializable{
    String no, name, address;
    float price;

    public Tour() {
    }

    public Tour(String no, String name, String address, float price) {
        this.no = no;
        this.name = name;
        this.address = address;
        this.price = price;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
}
