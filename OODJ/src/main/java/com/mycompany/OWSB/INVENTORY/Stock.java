/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.INVENTORY;

/**
 *
 * @author ooijinghui
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author cindy
 */
public class Stock {

    public enum ApprovalStatus {
        PENDING("PENDING"),
        APPROVED("APPROVED"),
        DISCLAIMED("DISCLAIMED");

        private final String displayName;

        ApprovalStatus(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }

        public static ApprovalStatus fromString(String text) {
            for (ApprovalStatus status : ApprovalStatus.values()) {
                if (status.displayName.equalsIgnoreCase(text)) {
                    return status;
                }
            }
            return null;
        }
    }

    public enum PaymentStatus {
        PAID("Paid"),
        UNPAID("Unpaid");

        private final String displayName;

        PaymentStatus(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }

        public static PaymentStatus fromString(String text) {
            for (PaymentStatus status : PaymentStatus.values()) {
                if (status.displayName.equalsIgnoreCase(text)) {
                    return status;
                }
            }
            return null;
        }
    }

    private String stockID;
    private String poID;
    private String itemID;
    private String itemName;
    private int newStockQuantity;
    private double totalPrice;
    private String supplier;
    private String stockArrivalDate;
    private int itemPreviousQuantity;
    private int itemCurrentQuantity;
    private ApprovalStatus stockApprovalStatus;
    private String stockApprovalDate;
    private PaymentStatus paymentStatus;

    public Stock(String stockID, String poID,String itemID, String itemName, int newStockQuantity, double totalPrice,
                 String supplier, String stockArrivalDate, int itemPreviousQuantity, int itemCurrentQuantity,
                 ApprovalStatus stockApprovalStatus, String stockApprovalDate, PaymentStatus paymentStatus) {

        this.stockID = stockID;
        this.poID = poID;
        this.itemID = itemID;
        this.itemName = itemName;
        this.newStockQuantity = newStockQuantity;
        this.totalPrice = totalPrice;
        this.supplier = supplier;
        this.stockArrivalDate = stockArrivalDate;
        this.itemPreviousQuantity = itemPreviousQuantity;
        this.itemCurrentQuantity = itemCurrentQuantity;
        this.stockApprovalStatus = stockApprovalStatus;
        this.stockApprovalDate = stockApprovalDate;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters (you can auto-generate these in IDE)
    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    
    public String getPoID() {
        return poID;
    }

    public void setPoID(String poID) {
        this.poID = poID;
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

    public int getNewStockQuantity() {
        return newStockQuantity;
    }

    public void setNewStockQuantity(int newStockQuantity) {
        this.newStockQuantity = newStockQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getStockArrivalDate() {
        return stockArrivalDate;
    }

    public void setStockArrivalDate(String stockArrivalDate) {
        this.stockArrivalDate = stockArrivalDate;
    }

    public int getItemPreviousQuantity() {
        return itemPreviousQuantity;
    }

    public void setItemPreviousQuantity(int itemPreviousQuantity) {
        this.itemPreviousQuantity = itemPreviousQuantity;
    }

    public int getItemCurrentQuantity() {
        return itemCurrentQuantity;
    }

    public void setItemCurrentQuantity(int itemCurrentQuantity) {
        this.itemCurrentQuantity = itemCurrentQuantity;
    }

    public ApprovalStatus getStockApprovalStatus() {
        return stockApprovalStatus;
    }

    public void setStockApprovalStatus(ApprovalStatus stockApprovalStatus) {
        this.stockApprovalStatus = stockApprovalStatus;
    }

    public String getStockApprovalDate() {
        return stockApprovalDate;
    }

    public void setStockApprovalDate(String stockApprovalDate) {
        this.stockApprovalDate = stockApprovalDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }



public static List<Stock> viewStockInFile() {
    List<Stock> stockList = new ArrayList<>();

    try {
        File file = new File("Stock.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "Stock.txt file does not exist.");
            return stockList;
        }

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        boolean isFirstLine = true; // Add this

        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false; // Skip header
                continue;
            }

            if (!line.trim().isEmpty()) {
                String[] row = line.split(";");
                if (row.length >= 13) { // 13 if your file includes PaymentDate

                    String stockID = row[0];
                    String poID = row[1];
                    String itemID = row[2];
                    String itemName = row[3];
                    int newStockQuantity = Integer.parseInt(row[4]);
                    double totalPrice = Double.parseDouble(row[5]);
                    String supplier = row[6];
                    String stockArrivalDate = row[7];
                    int itemPreviousQuantity = Integer.parseInt(row[8]);
                    int itemCurrentQuantity = Integer.parseInt(row[9]);
                    Stock.ApprovalStatus approvalStatus = Stock.ApprovalStatus.fromString(row[10]);
                    String approvalDate = row[11];
                    Stock.PaymentStatus paymentStatus = Stock.PaymentStatus.fromString(row[12]);

                    Stock stock = new Stock(stockID, poID, itemID, itemName, newStockQuantity, totalPrice,
                            supplier, stockArrivalDate, itemPreviousQuantity, itemCurrentQuantity,
                            approvalStatus, approvalDate, paymentStatus);

                    stockList.add(stock);
                    
                }
            }
        }
    }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error reading Stock.txt: " + e.getMessage());
    }

    return stockList;
}
    
}

