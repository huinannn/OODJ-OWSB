/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package com.mycompany.OWSB.FINANCE;
import com.mycompany.OWSB.SALES.*;
import java.awt.*;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Thong Hui Nan
 */

public class Finance_MakePayment extends javax.swing.JPanel {
    private final javax.swing.JPanel ChangePanel;
    private final String stockId;
    
    public Finance_MakePayment(JPanel ChangePanel, String stockId) {
        this.stockId = stockId;
        this.ChangePanel = ChangePanel;
        initComponents();
        loadPaymentData();
    }
    
    private void loadPaymentData() {
        try {
            Map<String, String> supplierIdNameMap = loadSupplierIdNameMap();
            String[] stockData = findStockById(stockId);

            if (stockData != null) {
                populatePaymentFields(stockData, supplierIdNameMap);
            } else {
                JOptionPane.showMessageDialog(this, "Stock ID not found.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading payment data: " + e.getMessage());
        }
    }
    
    private Map<String, String> loadSupplierIdNameMap() throws IOException {
        List<Suppliers> suppliers = Sales_Supplier.viewSuppliersInFile();
        Map<String, String> map = new HashMap<>();
        for (Suppliers supplier : suppliers) {
            map.put(supplier.getSupplierID(), supplier.getSupplierName());
        }
        return map;
    }

    private String[] findStockById(String stockId) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("Stock.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 10 && parts[0].equals(stockId)) {
                    return parts;
                }
            }
        }
        return null;
    }

    private void populatePaymentFields(String[] parts, Map<String, String> supplierMap) {
        StockID.setText(parts[0]);
        POID.setText(parts[1]);
        ItemID.setText(parts[2]);
        ItemName.setText(parts[3]);
        Quantity.setText(parts[4]);
        TotalPrice.setText("RM " + parts[5]);

        String supplierID = parts[6];
        String supplierName = supplierMap.getOrDefault(supplierID, "Unknown Supplier");
        Supplier.setText(supplierID + " - " + supplierName);

        StockApprovalStatus.setText(parts[7]);
    }

    private boolean validateInput(String referenceNo) {
        if (referenceNo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the details.");
            return false;
        }
        return true;
    }

    private boolean confirmPayment() {
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Are you sure you want to confirm this payment?", 
            "Confirm Payment", 
            JOptionPane.YES_NO_OPTION
        );
        return confirm == JOptionPane.YES_OPTION;
    }

    private boolean updateStockStatus(String stockId) throws IOException {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader("Stock.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 13 && parts[0].equals(stockId)) {
                    parts[12] = "Paid";
                    lines.add(String.join(";", parts));
                    found = true;
                } else {
                    lines.add(line);
                }
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Stock ID not found in Stock.txt.");
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Stock.txt"))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        }

        return true;
    }

    private void showSuccessMessage() {
        JOptionPane.showMessageDialog(this, "Payment successful.");
    }

    private void switchToReceiptPanel(String stockId, String referenceNo) {
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(new Finance_Receipt(
            ChangePanel,
            stockId,
            POID.getText(),
            ItemID.getText(),
            ItemName.getText(),
            Quantity.getText(),
            TotalPrice.getText(),
            Supplier.getText(),
            referenceNo,
            PaymentMethod_DropDown.getSelectedItem().toString()
        ), BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Item_Label = new javax.swing.JLabel();
        POID_Label = new javax.swing.JLabel();
        SaveButton = new javax.swing.JButton();
        PaymentDetails_Label = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();
        ReferenceNumber_Label = new javax.swing.JLabel();
        Quantity_Label = new javax.swing.JLabel();
        TotalPrice_Label = new javax.swing.JLabel();
        Supplier_Label = new javax.swing.JLabel();
        StockApprovalStatus_Label = new javax.swing.JLabel();
        PaymentMethod_Label = new javax.swing.JLabel();
        PaymentMethod_DropDown = new javax.swing.JComboBox<>();
        StockID = new javax.swing.JLabel();
        StockApprovalStatus = new javax.swing.JLabel();
        POID = new javax.swing.JLabel();
        TotalPrice = new javax.swing.JLabel();
        Item_Label1 = new javax.swing.JLabel();
        ItemName = new javax.swing.JLabel();
        Supplier = new javax.swing.JLabel();
        Quantity = new javax.swing.JLabel();
        ReferenceNumber_TextField = new javax.swing.JTextField();
        ItemID = new javax.swing.JLabel();
        StockID_Label = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        Item_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        Item_Label.setText("Item ID");

        POID_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        POID_Label.setText("PO ID");

        SaveButton.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        SaveButton.setText("Pay");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        PaymentDetails_Label.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        PaymentDetails_Label.setText("Payment Details");

        BackButton.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        BackButton.setText("Cancel");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        ReferenceNumber_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        ReferenceNumber_Label.setText("Reference Number");

        Quantity_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        Quantity_Label.setText("Quantity");

        TotalPrice_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        TotalPrice_Label.setText("Total Price");

        Supplier_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        Supplier_Label.setText("Supplier");

        StockApprovalStatus_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        StockApprovalStatus_Label.setText("Stock Approval Status");

        PaymentMethod_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        PaymentMethod_Label.setText("PaymentMethod");

        PaymentMethod_DropDown.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        PaymentMethod_DropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Online Banking", "E-wallets" }));
        PaymentMethod_DropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentMethod_DropDownActionPerformed(evt);
            }
        });

        Item_Label1.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        Item_Label1.setText("ItemName");

        StockID_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        StockID_Label.setText("Stock ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Item_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Quantity_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(TotalPrice_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Supplier_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(StockApprovalStatus_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(PaymentMethod_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(ReferenceNumber_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Item_Label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(17, 17, 17)))
                                .addGap(81, 81, 81))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(StockID_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(94, 94, 94))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(POID_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(105, 105, 105)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(PaymentMethod_DropDown, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(StockApprovalStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TotalPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Quantity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ItemID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ReferenceNumber_TextField)
                                    .addComponent(POID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(StockID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(137, 137, 137))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(ItemName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                    .addComponent(Supplier, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(BackButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29)
                        .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(200, 200, 200))))
            .addGroup(layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(PaymentDetails_Label)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(PaymentDetails_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(StockID_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(StockID, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(POID_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(POID, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Item_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(ItemID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ItemName, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(Item_Label1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(Quantity_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                        .addGap(7, 7, 7))
                    .addComponent(Quantity, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(TotalPrice_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Supplier, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(Supplier_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StockApprovalStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StockApprovalStatus_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PaymentMethod_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(PaymentMethod_DropDown, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ReferenceNumber_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(ReferenceNumber_TextField))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BackButton, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PaymentMethod_DropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentMethod_DropDownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PaymentMethod_DropDownActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        String referenceNo = ReferenceNumber_TextField.getText().trim();

        if (!validateInput(referenceNo)) return;
        if (!confirmPayment()) return;

        try {
            String stockId = StockID.getText();

            if (!updateStockStatus(stockId)) return;

            showSuccessMessage();
            switchToReceiptPanel(stockId, referenceNo);

        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error processing payment: " + e.getMessage());
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(new Finance_VerifyInventory(ChangePanel), BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_BackButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JLabel ItemID;
    private javax.swing.JLabel ItemName;
    private javax.swing.JLabel Item_Label;
    private javax.swing.JLabel Item_Label1;
    private javax.swing.JLabel POID;
    private javax.swing.JLabel POID_Label;
    private javax.swing.JLabel PaymentDetails_Label;
    private javax.swing.JComboBox<String> PaymentMethod_DropDown;
    private javax.swing.JLabel PaymentMethod_Label;
    private javax.swing.JLabel Quantity;
    private javax.swing.JLabel Quantity_Label;
    private javax.swing.JLabel ReferenceNumber_Label;
    private javax.swing.JTextField ReferenceNumber_TextField;
    private javax.swing.JButton SaveButton;
    private javax.swing.JLabel StockApprovalStatus;
    private javax.swing.JLabel StockApprovalStatus_Label;
    private javax.swing.JLabel StockID;
    private javax.swing.JLabel StockID_Label;
    private javax.swing.JLabel Supplier;
    private javax.swing.JLabel Supplier_Label;
    private javax.swing.JLabel TotalPrice;
    private javax.swing.JLabel TotalPrice_Label;
    // End of variables declaration//GEN-END:variables
}