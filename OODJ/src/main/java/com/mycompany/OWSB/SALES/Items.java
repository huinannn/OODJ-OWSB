/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.SALES;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author xiaochingloh
 */
public class Items {
    private String itemCode;
    private String itemName;
    private String supplierID;
    private double price;
    private int stockLevel;
    private String category;
    private String description;
    
    //Empty Constructor
    public Items(){}
    
    //Constructor
    public Items(String itemCode, String itemName, String supplierID, double price, int stockLevel, String category, String description) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

            try (
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw)
            ) {
                if (!fileExists) {
                    bw.write("ItemCode,ItemName,SupplierID,Price,StockLevel,Category,Description");
                    bw.newLine();
                }

                bw.write(item.getItemCode() + "," +
                         item.getItemName() + "," +
                         item.getSupplierID() + "," +
                         item.getPrice() + "," +
                         item.getStockLevel() + "," +
                         item.getCategory() + "," +
                         item.getDescription());
                bw.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
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

