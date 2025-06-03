/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.ADMIN;

/**
 *
 * @author cindy
 */
public class Admin extends User{
    public Admin(String employeeID, String username, String password){
        super(employeeID, username, password);
    }
    
    @Override
    public void accessDashboard(){
        new Admin_Dashboard().setVisible(true);
    }
}
