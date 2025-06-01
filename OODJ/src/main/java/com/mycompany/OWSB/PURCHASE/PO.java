/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.PURCHASE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author cindy
 */
public class PO {
    
    public enum PO_Status{
        PENDING("PENDING"),
        APPROVED("APPROVED"),
        DISCLAIMED("DISCLAIMED");
        
        private final String displayName;
        
        PO_Status(String displayName){
            this.displayName = displayName;
        }
        
        @Override
        public String toString(){
            return displayName;
        }
        
        public static PO_Status fromString(String text){
            for(PO_Status status : PO_Status.values()){
                if(status.displayName.equalsIgnoreCase(text)){
                    return status;
                }
            }
            
            return null;
        }
    }
    
    private String POID;
    private String itemID;
    private String itemName;
    private int Quantity;
    private double totalPrice;
    private String supplierID;
    private String raisedBy;
    private String deliveryDate;
    private PO_Status Status;
    
    public PO(String POID, String itemID, String itemName, int Quantity, double totalPrice, String supplierID, String raisedBy, String deliveryDate, PO_Status Status){
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

    public PO_Status getStatus() {
        return Status;
    }

    public void setStatus(PO_Status Status) {
        this.Status = Status;
    }
    
    public static List<PO> viewPOsInFile(){
        List<PO> poList = new ArrayList<>();
        
        try{
            File file = new File("PO_Lists.txt");
            if (!file.exists()){
                JOptionPane.showMessageDialog(null, "PO_Lists.txt file does not exist.");
                return poList;
            }
            
            try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
            ) {
                String line;
                while ((line = br.readLine()) != null) {    
                    if (!line.trim().isEmpty()){
                        String[] row = line.split(";");
                        if (row.length >= 9) {
                            String poID = row[0];
                            String itemID = row[1];
                            String itemName = row[2];
                            int quantity = Integer.parseInt(row[3]);
                            double totalPrice = Double.parseDouble(row[4]);
                            String supplierID = row[5];
                            String raisedBy = row[6];
                            String deliveryDate = row[7];
                            PO_Status poStatus = PO_Status.fromString(row[8]);
                            
                            PO po = new PO(poID, itemID, itemName, quantity, totalPrice, supplierID, raisedBy, deliveryDate, poStatus);
                            poList.add(po);
                            
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
        
        return poList;
    }
    
    
}
