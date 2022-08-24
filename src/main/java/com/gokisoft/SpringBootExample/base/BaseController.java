/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gokisoft.SpringBootExample.base;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Administrator
 */
public class BaseController {
    public EntityManagerFactory factory;
    
    public BaseController() {
        factory = Persistence.createEntityManagerFactory("com.gokisoft_SpringBootExample_jar_0.0.1-SNAPSHOTPU");
    }
}
