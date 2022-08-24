/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gokisoft.SpringBootExample.api;

import com.gokisoft.SpringBootExample.base.BaseController;
import com.gokisoft.SpringBootExample.datacontroller.ToursJpaController;
import com.gokisoft.SpringBootExample.entities.Tours;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping(value = "/api/tour")
public class ApiTourController extends BaseController{
    ToursJpaController jpaController;
    
    public ApiTourController() {
        jpaController = new ToursJpaController(factory);
    }
    
    @GetMapping("/list")
    public List<Tours> list() {
        List<Tours> dataList = jpaController.findToursEntities();
        
        return dataList;
    }
}
