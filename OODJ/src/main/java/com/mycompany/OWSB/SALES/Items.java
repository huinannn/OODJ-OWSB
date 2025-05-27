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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author xiaochingloh
 */
public class Items {
    //Fixed selection for category
    public enum Category{
        DRY_GOODS("Dry Goods"),
        BEVERAGES("Beverages"),
        FRESH_PRODUCE("Fresh Produce"),
        DIARY("Dairy"),
        FROZEN_FOODS("Frozen Foods"),
        CLEANING_SUPPLIES("Cleaning Supplies"),
        PERSONAL_CARE("Personal Care");
        
        private final String displayName;
        
        Category(String displayName){
            this.displayName = displayName;
        }
        
        @Override
        public String toString(){
            return displayName;
        }
        
        public static Category fromString(String text){
            for(Category cat : Category.values()){
                if(cat.displayName.equalsIgnoreCase(text)){
                    return cat;
                }
            }
            return null;
        }
    }
    private String itemCode;
    private String itemName;
    private String supplierID;
    private double price;
    private int stockLevel;
    private Category category;
    private String description;
    
    //Empty Constructor
    public Items(){}
    
    //Constructor
    public Items(String itemCode, String itemName, String supplierID, double price, int stockLevel, Category category, String description) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.supplierID = supplierID;
        this.price = price;
        this.stockLevel = stockLevel;
        this.category = category;
        this.description = description;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierId) {
        this.supplierID = supplierId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public static void saveItemToFile(Items item) {
        try {
            //Get Path
            String classPath = Items.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile(); 

            File dbDir = new File(baseDir.getParentFile(), "database");
            if (!dbDir.exists()) {
                dbDir.mkdirs(); 
            }

            File file = new File(dbDir, "Item.txt");
            boolean fileExists = file.exists();
            
            //Write File (Append)
            try (
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw)
            ) {
                if (!fileExists) {
                    bw.write("ItemCode,ItemName,SupplierID,Price,StockLevel,Category,Description");
                    bw.newLine();
                }
                
                String formattedPrice = String.format("%.2f", item.getPrice());

                bw.write(item.getItemCode() + "," +
                         item.getItemName() + "," +
                         item.getSupplierID() + "," +
                         formattedPrice + "," +
                         item.getStockLevel() + "," +
                         item.getCategory() + "," +
                         item.getDescription());
                bw.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static List<String[]> viewItemsInFile() {
        List<String[]> itemList = new ArrayList<>();
        
        try {
            String classPath = Items.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "Item.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "Item.txt file does not exist.");
                return itemList;
            }

            try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)
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
                        itemList.add(row);
                   }
               }
            }
            
            

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return itemList;
    }
    
    public String generateNextItemID(){
        List<String[]>itemList = Items.viewItemsInFile();
        int maxID = 0;
        
        for(String[] item : itemList){
            String id = item[0];
            if(id.startsWith("ITM")){
                try{
                    int numericPart = Integer.parseInt(id.substring(3));
                    if(numericPart > maxID){
                        maxID = numericPart;
                    }
                } catch(NumberFormatException e){
                    
                }
            }
        }
        
        int nextID = maxID +1;
        return String.format("ITM%03d", nextID);
    }
    
    public static Items getItemByCode(String itemCode) {
        System.out.println("Item: " + itemCode);
        try {
            String classPath = Items.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "Item.txt");

            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "Item.txt file does not exist.");
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

                    String[] parts = line.split(",");
                    if (parts.length >= 6 && parts[0].equals(itemCode)) {
                        String itemName = parts[1];
                        String supplierID = parts[2];
                        double price = Double.parseDouble(parts[3]);
                        int stockLevel = Integer.parseInt(parts[4]);
                        Category category = Category.fromString(parts[5]);
                        String description = (parts.length >= 7) ? parts[6] : "";
                        
                        return new Items(itemCode, itemName, supplierID, price, stockLevel, category, description);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading item: " + e.getMessage());
        }

        return null;
    }

    
    public static void editItemsInFile(String itemCode, Items updatedItem){
        try {
            String classPath = Items.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "Item.txt");

            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "Item.txt file does not exist.");
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

                String[] parts = line.split(",");
                    if (parts[0].equals(itemCode)) {
                        String formattedPrice = String.format("%.2f", updatedItem.getPrice());
                        String updatedLine = updatedItem.getItemCode() + "," +
                                updatedItem.getItemName() + "," +
                                updatedItem.getSupplierID() + "," +
                                formattedPrice + "," +
                                updatedItem.getStockLevel() + "," +
                                updatedItem.getCategory() + "," +
                                updatedItem.getDescription();
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
            
            JOptionPane.showMessageDialog(null, "Item updated successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error editing item: " + e.getMessage());
        }
    }
    
    public static void deleteItemsInFile(String itemCode){
        try {
            String classPath = Items.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "Item.txt");
            
            if(!file.exists()){
                 JOptionPane.showMessageDialog(null, "Item.txt file does not exist.");
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
                    
                    //Match itemCode
                    String[] parts = line.split(",");
                    if (!parts[0].equals(itemCode)) {
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
            JOptionPane.showMessageDialog(null, "Error deleting item: " + e.getMessage());
        }
    }


    
    //Debug
    @Override
    public String toString(){
        return "Item Code: " + itemCode +
                "\nItem Name: " + itemName +
                "\nSupplier ID: " + supplierID +
                "\nPrice: " + price + 
                "\nStock Level: " + stockLevel +
                "\nCategory: " + category +
                "\nDescription: " + description;
    }
}   

