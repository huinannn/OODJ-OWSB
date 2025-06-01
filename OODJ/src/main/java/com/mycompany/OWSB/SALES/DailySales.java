/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.SALES;

import java.time.LocalDate;
import java.util.List;

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
            
            //Add total sale amount to finance
//            double totalAmount = this.quantitySold * item.getUnitPrice();
        } else {
            System.out.println("Item with code " + this.itemCode + " not found.");
        }
    }
}
