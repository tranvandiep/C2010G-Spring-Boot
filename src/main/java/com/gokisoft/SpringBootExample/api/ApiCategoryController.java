/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gokisoft.SpringBootExample.api;

import com.gokisoft.SpringBootExample.base.BaseController;
import com.gokisoft.SpringBootExample.datacontroller.CategoryJpaController;
import com.gokisoft.SpringBootExample.datacontroller.exceptions.NonexistentEntityException;
import com.gokisoft.SpringBootExample.entities.Category;
import com.gokisoft.SpringBootExample.models.CategoryModel;
import com.gokisoft.SpringBootExample.models.ObjectMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/category")
public class ApiCategoryController extends BaseController{
    CategoryJpaController jpaController;
    
    public ApiCategoryController() {
        jpaController = new CategoryJpaController(factory);
    }
    
    @GetMapping("/list")
    public List<CategoryModel> list() {
        List<Category> dataList = jpaController.findCategoryEntities();
        List<CategoryModel> categoryList = new ArrayList<>();
        
        for (Category category : dataList) {
            categoryList.add(new CategoryModel(category.getId(), category.getName()));
        }
        
        return categoryList;
    }
    
    @PostMapping("/add")
    public ObjectMessage add(@RequestBody CategoryModel model) {
        Category cat = new Category();
        cat.setName(model.getName());
        
        jpaController.create(cat);
        
        return new ObjectMessage(1, "Success");
    }
    
    @PostMapping("/update")
    public ObjectMessage update(@RequestBody CategoryModel model) {
        Category cat = jpaController.findCategory(model.getId());
        cat.setName(model.getName());
        
        try {
            jpaController.edit(cat);
        } catch (Exception ex) {
            return new ObjectMessage(0, "Failed");
        }
        
        return new ObjectMessage(1, "Success");
    }
    
    @PostMapping("/delete")
    public ObjectMessage delete(@RequestBody CategoryModel model) {
        try {
            jpaController.destroy(model.getId());
        } catch (NonexistentEntityException ex) {
            return new ObjectMessage(0, "Failed");
        }
        
        return new ObjectMessage(1, "Success");
    }
    
    @GetMapping("/find/{id}")
    public CategoryModel find(@PathVariable int id) {
        Category cat = jpaController.findCategory(id);
        CategoryModel model = new CategoryModel();
        model.setModel(cat);
        
        return model;
    }
}
