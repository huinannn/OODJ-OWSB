/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.ADMIN;

/**
 *
 * @author cindy
 */
public class UserCreation {
    public static User createUser(String employeeID, String username, String password){
        if (employeeID.startsWith("SM"))
            return new SalesManager(employeeID, username, password);
        if (employeeID.startsWith("PM"))
            return new PurchaseManager(employeeID, username, password);
        if (employeeID.startsWith("IM"))
            return new InventoryManager(employeeID, username, password);
        if (employeeID.startsWith("FM"))
            return new FinanceManager(employeeID, username, password);
        if (employeeID.startsWith("Admin"))
            return new Admin(employeeID, username, password);
        return null;
    }
}
