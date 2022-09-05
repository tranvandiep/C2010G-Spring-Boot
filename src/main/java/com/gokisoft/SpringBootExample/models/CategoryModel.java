/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gokisoft.SpringBootExample.models;

import com.gokisoft.SpringBootExample.entities.Category;
import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class CategoryModel implements Serializable{
    int id;
    String name;

    public CategoryModel() {
    }

    public CategoryModel(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public void setModel(Category cat) {
        this.id = cat.getId();
        this.name = cat.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
