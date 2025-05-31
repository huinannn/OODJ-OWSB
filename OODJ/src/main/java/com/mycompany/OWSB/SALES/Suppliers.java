/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.SALES;


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
    private double unitPrice;
    
    //Empty Constructor
    public Suppliers(){}
    
    //Constructor
    public Suppliers(String supplierID, String supplierName, String contactPerson, String phone, String email, String address, String itemSupplied,  double unitPrice){
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.itemSupplied = itemSupplied;
        this.unitPrice = unitPrice;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }    
}
