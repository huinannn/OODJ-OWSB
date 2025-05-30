/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.ADMIN;

import com.mycompany.OWSB.PURCHASE.Purchase_Dashboard;



/**
 *
 * @author cindy
 */
class PurchaseManager extends User{
    public PurchaseManager(String employeeID, String username, String password){
        super(employeeID, username, password, "PM");
    }
    
    @Override
    public void accessDashboard(){
        new Purchase_Dashboard().setVisible(true);
    }
}
