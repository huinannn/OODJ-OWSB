/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.ADMIN;

import com.mycompany.OWSB.INVENTORY.*;


/**
 *
 * @author cindy
 */
public class InventoryManager extends User{
    public InventoryManager(String employeeID, String username, String password){
        super(employeeID, username, password, "IM");
    }
    
    @Override
    public void accessDashboard(){
        new IM_Dashboard().setVisible(true); //wrong dashboard, waiting for inventory dashboard
    }
}
