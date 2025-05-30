/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.ADMIN;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cindy
 */
public abstract class User {
    protected String employeeID;
    protected String username;
    protected String password;

    
    public User(String employeeID, String username, String password, String role){
        this.employeeID = employeeID;
        this.username = username;
        this.password = password;  
    }
    
    public abstract void accessDashboard();
    
    public boolean updateCredentials(String newUsername, String newPassword) {
        File file = new File("login.txt");
        List<String> updatedLines = new ArrayList<>();
        boolean updated = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5 && parts[0].trim().equals(employeeID)) {
                    // Update username and password, keep attempts and status as is
                    String attempts = parts[3].trim();
                    String status = parts[4].trim();
                    String newLine = employeeID + "," + newUsername + "," + newPassword + "," + attempts + "," + status;
                    updatedLines.add(newLine);
                    updated = true;
                } else {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            return false;
        }

        // Write back updated lines
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String updatedLine : updatedLines) {
                bw.write(updatedLine);
                bw.newLine();
            }
        } catch (IOException e) {
            return false;
        }

        return updated;
    }
}
