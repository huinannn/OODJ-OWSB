/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.SALES;

/**
 *
 * @author xiaochingloh
 */
public class Items {
    private String itemCode;
    private String itemName;
    private String supplierId;
    private double price;
    private int stockLevel;
    private String category;
    private String description;
    
    //Empty Constructor
    public Items(){}
    
    //Constructor
    public Items(String itemCode, String itemName, String supplierId, double price, int stockLevel, String category, String description) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.supplierId = supplierId;
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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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
    
    //Debug
    @Override
    public String toString(){
        return "Item Code: " + itemCode +
                "\nItem Name: " + itemName +
                "\nSupplier ID: " + supplierId +
                "\nPrice: " + price + 
                "\nStock Level: " + stockLevel +
                "\nCategory: " + category +
                "\nDescription: " + description;
    }
}   

