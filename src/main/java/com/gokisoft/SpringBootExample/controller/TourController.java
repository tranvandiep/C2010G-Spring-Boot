/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gokisoft.SpringBootExample.controller;

import com.gokisoft.SpringBootExample.models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/tour")
public class TourController {
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("msg", "Sinh vien Aptech 54 Le Thanh Nghi");
        
        Student std = new Student("A", "a@gmail.com");
        model.addAttribute("std", std);
        
        return "tour/index";
    }
}
