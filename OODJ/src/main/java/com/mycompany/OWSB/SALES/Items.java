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
    private String description;
    private ReorderAlertStatus reorderStatus;
    
    //Empty Constructor
    public Items(){}
    
    //Sales Constructor
    public Items(String itemCode, String itemName, Category category, int stockCurrentQuantities, int reorderLevel, String description, ReorderAlertStatus reorderStatus) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.category = category;
        this.stockCurrentQuantities = stockCurrentQuantities;
        this.reorderLevel = reorderLevel;
        this.description = description;
        this.reorderStatus = reorderStatus;
    }
    
    //Inventory Constructor
    public Items(String itemCode, String itemName, Category category, int stockCurrentQuantities, int reorderLevel, ReorderAlertStatus reorderStatus) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.category = category;
        this.stockCurrentQuantities = stockCurrentQuantities;
        this.reorderLevel = reorderLevel;
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


    
    //Debug
//    @Override
//    public String toString(){
//        return "Item Code: " + itemCode +
//                "\nItem Name: " + itemName +
//                "\nUnit Price: " + unitPrice + 
//                "\nStock Current Quantities: " + stockCurrentQuantities +
//                "\nReorder Level: " + reorderLevel +
//                "\nCategory: " + category +
//                "\nDescription: " + description +
//                "\nReorder Alert Status: " + reorderStatus;
//    }
}   

