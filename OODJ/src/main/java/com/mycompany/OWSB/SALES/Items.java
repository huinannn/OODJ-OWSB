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
    //Fix selection for reorder alert status
    public enum ReorderAlertStatus{
        LOW_STOCK("LOW STOCK"),
        STOCK_READY("STOCK READY");
        
        private final String displayName;
        
        ReorderAlertStatus(String displayName){
            this.displayName = displayName;
        }
        
        @Override
        public String toString(){
            return displayName;
        }
        
        public static ReorderAlertStatus fromString(String text){
            for(ReorderAlertStatus r : ReorderAlertStatus.values()){
                if(r.displayName.equalsIgnoreCase(text)){
                    return r;
                }
            }
            return null;
        }
    }
    private String itemCode;
    private String itemName;
    private Category category;
    private int stockCurrentQuantities;
    private int reorderLevel;
    private double unitPrice;
    private String description;
    private ReorderAlertStatus reorderStatus;
    
    //Empty Constructor
    public Items(){}
    
    //Constructor
    public Items(String itemCode, String itemName, Category category, int stockCurrentQuantities, int reorderLevel, double unitPrice, String description, ReorderAlertStatus reorderStatus) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.category = category;
        this.stockCurrentQuantities = stockCurrentQuantities;
        this.reorderLevel = reorderLevel;
        this.unitPrice = unitPrice;
        this.description = description;
        this.reorderStatus = reorderStatus;
    }

    public ReorderAlertStatus getReorderStatus() {
        return reorderStatus;
    }

    public void setReorderStatus(ReorderAlertStatus reorderStatus) {
        this.reorderStatus = reorderStatus;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStockCurrentQuantities() {
        return stockCurrentQuantities;
    }

    public void setStockCurrentQuantities(int stockCurrentQuantities) {
        this.stockCurrentQuantities = stockCurrentQuantities;
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

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
    
    public String generateNextItemID(){
        List<Items>itemList = Items.viewItemsInFile();
        int maxID = 0;
        
        for(Items item : itemList){
             String id = item.getItemCode();
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
    
    public static void saveItemToFile(Items item) {
        try {
            //Get Path
            String classPath = Items.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile(); 

            File dbDir = new File(baseDir.getParentFile(), "database");
            if (!dbDir.exists()) {
                dbDir.mkdirs(); 
            }

            File file = new File(dbDir, "Inventory.txt");
            boolean fileExists = file.exists();
            
            //Write File (Append)
            try (
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw)
            ) {
                if (!fileExists) {
                    bw.write("ItemCode;ItemName;Category;StockCurrentQuantities;ReorderLevel;UnitPrice;Description;ReorderAlertStatus");
                    bw.newLine();
                }
                
                String formattedPrice = String.format("%.2f", item.getUnitPrice());

                bw.write(item.getItemCode() + ";" +
                         item.getItemName() + ";" +
                         item.getCategory() + ";" +
                         item.getStockCurrentQuantities() + ";" +
                         item.getReorderLevel() + ";" +
                         formattedPrice + ";" +
                         item.getDescription() + ";" +
                         item.getReorderStatus());
                bw.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static List<Items> viewItemsInFile() {
        List<Items> itemList = new ArrayList<>();
        
        try {
            String classPath = Items.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "Inventory.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "Inventory.txt file does not exist.");
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
                   
                    if (!line.trim().isEmpty()) {
                        String[] row = line.split(";");
                        if (row.length >= 8) {
                            Category category = Category.fromString(row[2]);
                            ReorderAlertStatus status = ReorderAlertStatus.fromString(row[7]);
                            int quantity = Integer.parseInt(row[3]);
                            int reorder_level = Integer.parseInt(row[4]);
                            double price = Double.parseDouble(row[5]); 
                            Items item = new Items(
                                row[0], 
                                row[1], 
                                category, 
                                quantity,
                                reorder_level,
                                price, 
                                row[6], 
                                status
                            );
                            itemList.add(item);
                        }
                    }
               }
            }
            
            

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return itemList;
    }
    
    public static Items getItemByCode(String itemCode) {
        try {
            String classPath = Items.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "Inventory.txt");

            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "Inventory.txt file does not exist.");
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
                    if (parts.length >= 9 && parts[0].equals(itemCode)) {
                        String itemName = parts[1];
                        Category category = Category.fromString(parts[3]);
                        int stockCurrentQuantities = Integer.parseInt(parts[4]);
                        int reorderLevel = Integer.parseInt(parts[5]);
                        double unitPrice = Double.parseDouble(parts[6]);
                        String description = !parts[7].equals("") ? parts[7] : "";
                        ReorderAlertStatus reorderStatus = ReorderAlertStatus.fromString(parts[8]);
                        
                        return new Items(itemCode, itemName,category, stockCurrentQuantities, reorderLevel, unitPrice, description, reorderStatus);
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
            File file = new File(dbDir, "Inventory.txt");

            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "Inventory.txt file does not exist.");
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
                    if (parts[0].equals(itemCode)) {
                        String formattedPrice = String.format("%.2f", updatedItem.getUnitPrice());
                        String updatedLine = updatedItem.getItemCode() + ";" +
                                updatedItem.getItemName() + ";" +
                                updatedItem.getCategory() + ";" +
                                updatedItem.getStockCurrentQuantities() + ";" +
                                updatedItem.getReorderLevel() + ";" +
                                formattedPrice + ";" +
                                updatedItem.getDescription() + ";" +
                                updatedItem.getReorderStatus();
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
            File file = new File(dbDir, "Inventory.txt");
            
            if(!file.exists()){
                 JOptionPane.showMessageDialog(null, "Inventory.txt file does not exist.");
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
                    String[] parts = line.split(";");
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
                "\nUnit Price: " + unitPrice + 
                "\nStock Current Quantities: " + stockCurrentQuantities +
                "\nReorder Level: " + reorderLevel +
                "\nCategory: " + category +
                "\nDescription: " + description +
                "\nReorder Alert Status: " + reorderStatus;
    }
}   

