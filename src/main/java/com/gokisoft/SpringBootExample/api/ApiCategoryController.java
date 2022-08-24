/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gokisoft.SpringBootExample.api;

import com.gokisoft.SpringBootExample.base.BaseController;
import com.gokisoft.SpringBootExample.datacontroller.CategoryJpaController;
import com.gokisoft.SpringBootExample.entities.Category;
import com.gokisoft.SpringBootExample.models.CategoryModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
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
}
