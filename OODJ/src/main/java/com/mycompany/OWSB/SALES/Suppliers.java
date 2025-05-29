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
import java.util.Arrays;
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
    private String itemSupplied;
    
    //Empty Constructor
    public Suppliers(){}
    
    //Constructor
    public Suppliers(String supplierID, String supplierName, String contactPerson, String phone, String email, String address, String itemSupplied){
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

    public String getItemSupplied() {
        return itemSupplied;
    }

    public void setItemSupplied(String itemSupplied) {
        this.itemSupplied = itemSupplied;
    }
    
    public String generateNextSupplierID(){
        List<Suppliers>supplierList = Suppliers.viewSuppliersInFile();
        int maxID = 0;
        
        for(Suppliers supplier : supplierList){
            String id = supplier.getSupplierID();
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
                    bw.write("SupplierID;SupplierName;ContactPerson;Phone;Email;Address;ItemCode");
                    bw.newLine();
                }
                
                bw.write(supplier.getSupplierID() + ";" +
                         supplier.getSupplierName() + ";" +
                         supplier.getContactPerson() + ";" +
                         supplier.getPhone() + ";" + 
                         supplier.getEmail() + ";" +
                         supplier.getAddress() + ";" +
                         supplier.getItemSupplied());
                bw.newLine();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static List<Suppliers> viewSuppliersInFile(){
        List<Suppliers> supplierList = new ArrayList<>();
        
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
                        String[] row = line.split(";");
                        if (row.length >= 7) {
                            Suppliers supplier = new Suppliers(
                                row[0], 
                                row[1], 
                                row[2], 
                                row[3], 
                                row[4], 
                                row[5], 
                                row[6]
                            );
                            supplierList.add(supplier);
                        } else {
                            System.err.println("Invalid row: " + Arrays.toString(row));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return supplierList;
    }
    
    public static Suppliers getSupplierByID(String SupplierID){
        try{
            String classPath = Suppliers.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "Supplier.txt");

            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "Supplier.txt file does not exist.");
                return null;
            }
            
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                boolean isFirstLine = true;

                while ((line = br.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }

                    String[] parts = line.split(";");
                    if (parts.length >= 7 && parts[0].equals(SupplierID)) {
                        String supplierName = parts[1];
                        String contactPerson = parts[2];
                        String phone = parts[3];
                        String email = parts[4];
                        String address = parts[5];
                        String itemCode = parts[6];
                        
                        return new Suppliers(SupplierID, supplierName, contactPerson, phone, email, address, itemCode);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading supplier: " + e.getMessage());
        }
        
        return null;
    }
    
    public List<Suppliers> findByItemCode(String itemCode){
        List<Suppliers> matchingSuppliers = new ArrayList<>();
        List<Suppliers> allSuppliers = viewSuppliersInFile();
        for (Suppliers supplier : allSuppliers){
            String suppliedItemCode = supplier.getItemSupplied().split(":")[0].trim();
            if (suppliedItemCode.equals(itemCode)){
                matchingSuppliers.add(supplier);
            }
        }
        
        return matchingSuppliers;
    }
    
    public static void editSuppliersInFile(String supplierID, Suppliers updatedSupplier){
        try {
            String classPath = Suppliers.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "Supplier.txt");

            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "Supplier.txt file does not exist.");
                return;
            }

            List<String> lines = new ArrayList<>();
            boolean isFirstLine = true;

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while((line = br.readLine())!= null){
                    if (isFirstLine) {
                        lines.add(line); 
                        isFirstLine = false;
                        continue;
                    }

                String[] parts = line.split(";");
                    if (parts[0].equals(supplierID)) {
                        String updatedLine = updatedSupplier.getSupplierID() + ";" +
                                updatedSupplier.getSupplierName() + ";" +
                                updatedSupplier.getContactPerson() + ";" +
                                updatedSupplier.getPhone() + ";" +
                                updatedSupplier.getEmail() + ";" +
                                updatedSupplier.getAddress() + ";" +
                                updatedSupplier.getItemSupplied();
                        lines.add(updatedLine);
                    } else {
                        lines.add(line);
                    }
                }
            }
            
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
                for (String l : lines){
                    bw.write(l);
                    bw.newLine();
                }
            }
            
            JOptionPane.showMessageDialog(null, "Supplier updated successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error editing supplier: " + e.getMessage());
        }
    }
    
    public static void deleteSuppliersInFile(String supplierID){
        try {
            String classPath = Suppliers.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "Supplier.txt");
            
            if(!file.exists()){
                 JOptionPane.showMessageDialog(null, "Supplier.txt file does not exist.");
                return;
            }
            
            List<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                boolean isFirstLine = true;
                while ((line = br.readLine()) != null) {
                    if (isFirstLine) {
                        lines.add(line);
                        isFirstLine = false;
                        continue;
                    }
                    
                    //Match supplierID
                    String[] parts = line.split(";");
                    if (!parts[0].equals(supplierID)) {
                        lines.add(line);
                    }
                }
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (String l : lines) {
                    bw.write(l);
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting supplier: " + e.getMessage());
        }
    }
}
