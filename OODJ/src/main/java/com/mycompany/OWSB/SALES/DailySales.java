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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author xiaochingloh
 */
public class DailySales {
    private String salesID;
    private String itemCode;
    private int quantitySold;
    private LocalDate date;
    
    //Empty Constructor
    public DailySales(){}
    
    //Constructor
    public DailySales(String salesID, String itemCode, int quantitySold, LocalDate date){
        this.salesID = salesID;
        this.itemCode = itemCode;
        this.quantitySold = quantitySold;
        this.date = date;
    }

    public String getSalesID() {
        return salesID;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    // Reduces stock & Add revenue after sales
    public void reduceStock() {
        Items item = Sales_EditItem.getItemByCode(this.itemCode);
        if (item != null){
            //Reduce Stock
            int currentQuantity = item.getStockCurrentQuantities();
            item.setStockCurrentQuantities(currentQuantity - this.quantitySold);
            
            Sales_EditItem.editItemsInFile(this.itemCode, item);
            
        } else {
            System.out.println("Item with code " + this.itemCode + " not found.");
        }
    }
    
    public double calculateTotalSales() {
        Items item = Sales_EditItem.getItemByCode(this.itemCode);
        if (item != null) {
            // Add total sale amount to finance
            double totalAmount = this.quantitySold * item.getUnitPrice();
            return totalAmount;
        }
        return 0.0;
    }
    
    
    public static void saveDSToFile(DailySales ds){
        try{
            //Get Path
            String classPath = DailySales.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            
            File dbDir = new File(baseDir.getParentFile(), "database");
            if (!dbDir.exists()){
                dbDir.mkdirs();
            }
            
            File file = new File(dbDir, "DailySales.txt");
            boolean fileExists = file.exists();
            
            //Write File (Append)
            try (
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
            ) {
                if (!fileExists){
                    bw.write("SalesID;ItemCode;QuantitySold;DateOfSales");
                    bw.newLine();
                }
                
                bw.write(ds.getSalesID() + ";" +
                         ds.getItemCode() + ";" +
                         ds.getQuantitySold() + ";" +
                         ds.getDate());
                bw.newLine();
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static DailySales getDSByID(String salesID){
        try{
            String classPath = DailySales.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "DailySales.txt");
            
            if (!file.exists()){
                JOptionPane.showMessageDialog(null, "DailySales.txt file does not exist.");
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
                    if (parts.length >= 4 && parts[0].equals(salesID)){
                        String itemCode = parts[1];
                        int quantitySold = Integer.parseInt(parts[2]);
                        LocalDate date = LocalDate.parse(parts[3]);
                        
                        return new DailySales(salesID, itemCode, quantitySold, date);
                                
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading supplier: " + e.getMessage());
        }
        
        return null;
    }
    
    public static List<DailySales> viewDSInFile(){
        List<DailySales> dsList = new ArrayList<>();
        
        try{
            String classPath = DailySales.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "DailySales.txt");
            if (!file.exists()){
                JOptionPane.showMessageDialog(null, "DailySales.txt file does not exist.");
                return dsList;
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
                        if (row.length >= 4){
                            int quantitySold = Integer.parseInt(row[2]);
                            LocalDate date = LocalDate.parse(row[3]);
                            DailySales ds = new DailySales(
                                    row[0],
                                    row[1],
                                    quantitySold,
                                    date
                            );
                            dsList.add(ds);
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
        
        return dsList;
    }
    
    public static void editDSInFile(String salesID, DailySales updatedSales){
        try{
            String classPath = DailySales.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "DailySales.txt");
            
            if(!file.exists()){
                JOptionPane.showMessageDialog(null, "DailySales.txt file does not exist.");
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
                    if (parts[0].equals(salesID)){
                        String updatedLine = updatedSales.getSalesID() + ";" +
                                updatedSales.getItemCode() + ";" +
                                updatedSales.getQuantitySold() + ";" +
                                updatedSales.getDate();
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
            
            JOptionPane.showMessageDialog(null, "Sales updated successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error editing supplier: " + e.getMessage());
        }
    }
    
    public static void deleteDSInFile(String salesID){
        try{
            String classPath = DailySales.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "DailySales.txt");
            
            if(!file.exists()){
                 JOptionPane.showMessageDialog(null, "DailySales.txt file does not exist.");
                return;
            }
            
            List<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                boolean isFirstLine = true;
                while((line = br.readLine()) != null){
                    if (isFirstLine){
                        lines.add(line);
                        isFirstLine = false;
                        continue;
                    }
                    
                    //Match salesID
                    String[] parts = line.split(";");
                    if (!parts[0].equals(salesID)){
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
