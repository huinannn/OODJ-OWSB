/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.PURCHASE;

/**
 *
 * @author cindy
 */
public class PO {
    private String POID;
    private String itemID;
    private String itemName;
    private int Quantity;
    private double totalPrice;
    private String supplierID;
    private String raisedBy;
    private String deliveryDate;
    private String Status;
    
    public PO(String POID, String itemID, String itemName, int Quantity, double totalPrice, String supplierID, String raisedBy, String deliveryDate, String Status){
        this.POID = POID;
        this.itemID = itemID;
        this.itemName = itemName;
        this.Quantity = Quantity;
        this.totalPrice = totalPrice;
        this.supplierID = supplierID;
        this.raisedBy = raisedBy;
        this.deliveryDate = deliveryDate;
        this.Status = Status;
    }

    public String getPOID() {
        return POID;
    }

    public void setPOID(String POID) {
        this.POID = POID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getRaisedBy() {
        return raisedBy;
    }

    public void setRaisedBy(String raisedBy) {
        this.raisedBy = raisedBy;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
    
    
}
