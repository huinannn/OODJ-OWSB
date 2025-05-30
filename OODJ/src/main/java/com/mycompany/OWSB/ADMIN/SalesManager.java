/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.ADMIN;

import com.mycompany.OWSB.SALES.Sales_Dashboard;



/**
 *
 * @author cindy
 */
class SalesManager extends User{
    public SalesManager(String employeeID, String username, String password){
        super(employeeID, username, password, "SM");
    }
    
    @Override
    public void accessDashboard(){
        new Sales_Dashboard().setVisible(true);
    }
}
