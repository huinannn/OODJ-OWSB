/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.SALES;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author TP070386
 */
public class Suppliers {
    private String supplierID;
    private String supplierName;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;
    private List<String> itemSupplied;
    
    //Empty Constructor
    public Suppliers(){}
    
    //Constructor
    public Suppliers(String supplierID, String supplierName, String contactPerson, String phone, String email, String address, List<String> itemSupplied){
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.itemSupplied = itemSupplied; 
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getItemSupplied() {
        return itemSupplied;
    }

    public void setItemSupplied(List<String> itemSupplied) {
        this.itemSupplied = itemSupplied;
    }
    
    public String generateNextSupplierID(){
        List<String[]>supplierList = Suppliers.viewSuppliersInFile();
        int maxID = 0;
        
        for(String[] supplier : supplierList){
            String id = supplier[0];
            if(id.startsWith("SUP")){
                try{
                    int numericPart = Integer.parseInt(id.substring(3));
                    if(numericPart > maxID){
                        maxID = numericPart;
                    }
                } catch (NumberFormatException e){
                    
                }
            }
        }
        
        int nextID = maxID +1;
        return String.format("SUP%03d", nextID);
    }
    
    public static void saveSupplierToFile(Suppliers supplier){
        try{
            //Get Path
            String classPath = Suppliers.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            
            File dbDir = new File(baseDir.getParentFile(), "database");
            if (!dbDir.exists()){
                dbDir.mkdirs();
            }
            
            File file = new File(dbDir, "Supplier.txt");
            boolean fileExists = file.exists();
            
            //Write File (Append)
            try (
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
            ) {
                if (!fileExists){
                    bw.write("SupplierID,SupplierName,ContactPerson,Phone,Email,Address,ItemSupplied");
                    bw.newLine();
                }
                
                bw.write(supplier.getSupplierID() + "," +
                         supplier.getSupplierName() + "," +
                         supplier.getContactPerson() + "," +
                         supplier.getPhone() + "," + 
                         supplier.getEmail() + "," +
                         supplier.getAddress() + "," +
                         supplier.getItemSupplied());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static List<String[]> viewSuppliersInFile(){
        List<String[]> supplierList = new ArrayList<>();
        
        try{
            String classPath = Suppliers.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "Supplier.txt");
            if (!file.exists()){
                JOptionPane.showMessageDialog(null, "Supplier.txt file does not exist.");
                return supplierList;
            }
            
            try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
            ) {
                String line;
                boolean isFirstLine = true;
                
                while((line = br.readLine()) != null){
                    if (isFirstLine){
                        isFirstLine = false;
                        continue;
                    }
                    
                    if (!line.trim().isEmpty()){
                        String[] row = line.split(",");
                        supplierList.add(row);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return supplierList;
    }
}
