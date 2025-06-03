/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.ADMIN;

/**
 *
 * @author cindy
 */
public class Session {
    private static String employeeID;
    private static String username;
    private static String password;
    
    public static void setSession(String empID, String usern, String passw) {
        employeeID = empID;
        username = usern;
        password = passw;
    }
    
    public static String getEmployeeID() {
        return employeeID;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setUsername(String username) {
        Session.username = username;
    }

    public static void setPassword(String password) {
        Session.password = password;
    }
    
    

    public static void clearSession() {
        employeeID = null;
        username = null;
        password = null;
    }
    
    
}
