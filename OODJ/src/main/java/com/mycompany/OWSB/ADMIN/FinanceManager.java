/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.ADMIN;

import com.mycompany.OWSB.FINANCE.Finance_Dashboard;


/**
 *
 * @author cindy
 */
public class FinanceManager extends User{
    public FinanceManager(String employeeID, String username, String password){
        super(employeeID, username, password);
    }
    
    @Override
    public void accessDashboard(){
        new Finance_Dashboard().setVisible(true);
        
    }
}
