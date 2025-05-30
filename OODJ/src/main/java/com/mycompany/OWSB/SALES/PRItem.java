/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.SALES;


/**
 *
 * @author xiaochingloh
 */
class PRItem {
    private String itemCode;
    private String itemName;
    private int quantity;
    private String supplierID;
    
    //Empty Constructor
    public PRItem(){}
    
    //Constructor
    public PRItem(String itemCode, String itemName, int quantity, String supplierID){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.supplierID = supplierID;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }
    
    
}
