/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.OWSB.FINANCE;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Thong Hui Nan
 */

public class Payment {
    private String receiptNo;
    private String date;
    private String POId;
    private String itemId;
    private String itemName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String supplierId;
    private String supplierName;
    private String paymentMethod;
    private String referenceNo;
    private String preparedById;
    private String preparedByRole;
    
    public void Payment(
        String receiptNo, String date, String poId, String itemId, String itemName, String quantity,
        String unitPrice, String totalPrice, String supplierInfo, String paymentMethod,
        String referenceNo, String preparedBy
    ) {
        setReceiptNo(receiptNo);
        setDate(date);
        setPOId(poId);
        setItemId(itemId);
        setItemName(itemName);

        try {
            setQuantity(Integer.parseInt(quantity));
        } catch (NumberFormatException e) {
            setQuantity(0);
        }

        try {
            setUnitPrice(Double.parseDouble(unitPrice.replace("RM", "").trim()));
        } catch (NumberFormatException e) {
            setUnitPrice(0.0);
        }

        try {
            setTotalPrice(Double.parseDouble(totalPrice.replace("RM", "").trim()));
        } catch (NumberFormatException e) {
            setTotalPrice(0.0);
        }

        if (supplierInfo.contains(" - ")) {
            String[] parts = supplierInfo.split(" - ", 2);
            setSupplierId(parts[0]);
            setSupplierName(parts[1]);
        } else {
            setSupplierId("-");
            setSupplierName(supplierInfo);
        }

        setPaymentMethod(paymentMethod);
        setReferenceNo(referenceNo);

        if (preparedBy.contains(" - ")) {
            String[] parts = preparedBy.split(" - ", 2);
            setPreparedById(parts[0]);
            setPreparedByRole(parts[1]);
        } else {
            setPreparedById("-");
            setPreparedByRole(preparedBy);
        }
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPOId() {
        return POId;
    }

    public void setPOId(String POId) {
        this.POId = POId;
    }
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getPreparedById() {
        return preparedById;
    }

    public void setPreparedById(String preparedById) {
        this.preparedById = preparedById;
    }

    public String getPreparedByRole() {
        return preparedByRole;
    }

    public void setPreparedByRole(String preparedByRole) {
        this.preparedByRole = preparedByRole;
    }

    public String getMonth() {
        return date.substring(0, 7);
    }

    public String getYear() {
        return date.substring(0, 4);
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    
    public static String getUnitPriceFromInventory(String itemCode) {
        try (BufferedReader br = new BufferedReader(new FileReader("database/Inventory.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 6 && parts[0].equals(itemCode)) {
                    return parts[5];
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading inventory file: " + e.getMessage());
        }
        return "0.00";
    }
    
    public static String calculateTotalPrice(String quantity, String unitPrice) {
        try {
            double qty = Double.parseDouble(quantity);
            double unit = Double.parseDouble(unitPrice);
            return String.format("RM %.2f", qty * unit);
        } catch (NumberFormatException e) {
            return "RM 0.00";
        }
    }
    
    public String toPrintableString() {
        return String.format(
            "-----------------------------------------------------\n" +
            "                  PAYMENT RECEIPT\n" +
            "-----------------------------------------------------\n" +
            "Receipt No.        : %s\n" +
            "Date               : %s\n\n" +
            "POID               : %s\n"+
            "Item               : %s - %s\n" +
            "Quantity           : %d\n" +
            "Unit Price         : RM %.2f\n" +
            "Total Price        : RM %.2f\n\n" +
            "Supplier           : %s - %s\n" +
            "Payment Method     : %s\n" +
            "Reference No.      : %s\n\n" +
            "Prepared By        : %s - %s\n" +
            "-----------------------------------------------------\n" +
            "             Thank you for your payment!\n" +
            "-----------------------------------------------------\n",
            receiptNo, date, POId, itemId, itemName, quantity, unitPrice, totalPrice,
            supplierId, supplierName, paymentMethod, referenceNo, preparedById, preparedByRole
        );
    }
    
    public boolean saveReceipt(JPanel parentPanel) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Receipt.txt", true))) {
            bw.write(this.toPrintableString());
            bw.newLine();
            JOptionPane.showMessageDialog(parentPanel, "Receipt saved successfully!");
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parentPanel, "Error saving receipt: " + e.getMessage());
            return false;
        }
    }

    public static String generateNewReceiptID() {
        int lastNum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("Receipt.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.matches(".*RCPT\\d{3}.*")) {
                    int index = line.indexOf("RCPT");
                    if (index != -1 && line.length() >= index + 7) {
                        String numStr = line.substring(index + 4, index + 7);
                        try {
                            int num = Integer.parseInt(numStr);
                            if (num > lastNum) lastNum = num;
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.format("RCPT%03d", lastNum + 1);
    }
     
     public void populateFromFields(
        String receiptNo, String date, String poId, String itemId, String itemName, String quantity,
        String unitPrice, String totalPrice, String supplierInfo, String paymentMethod,
        String referenceNo, String preparedBy
    ) {
        setReceiptNo(receiptNo);
        setDate(date);
        setPOId(poId);
        setItemId(itemId);
        setItemName(itemName);

        try {
            setQuantity(Integer.parseInt(quantity));
        } catch (NumberFormatException e) {
            setQuantity(0);
        }

        try {
            setUnitPrice(Double.parseDouble(unitPrice.replace("RM", "").trim()));
        } catch (NumberFormatException e) {
            setUnitPrice(0.0);
        }

        try {
            setTotalPrice(Double.parseDouble(totalPrice.replace("RM", "").trim()));
        } catch (NumberFormatException e) {
            setTotalPrice(0.0);
        }

        if (supplierInfo.contains(" - ")) {
            String[] parts = supplierInfo.split(" - ", 2);
            setSupplierId(parts[0]);
            setSupplierName(parts[1]);
        } else {
            setSupplierId("-");
            setSupplierName(supplierInfo);
        }

        setPaymentMethod(paymentMethod);
        setReferenceNo(referenceNo);

        if (preparedBy.contains(" - ")) {
            String[] parts = preparedBy.split(" - ", 2);
            setPreparedById(parts[0]);
            setPreparedByRole(parts[1]);
        } else {
            setPreparedById("-");
            setPreparedByRole(preparedBy);
        }
    }
}