/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package com.mycompany.OWSB.FINANCE;
import com.mycompany.OWSB.ADMIN.Session;
import java.awt.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Thong Hui Nan
 */

public class Finance_Receipt extends javax.swing.JPanel {
    private final javax.swing.JPanel ChangePanel;
    private final Payment payment = new Payment();
    
    public Finance_Receipt(JPanel ChangePanel, String stockId, String POId, String itemId, String itemName, String quantity, String totalPrice, String supplier, String referenceNo, String paymentMethod) {
        this.ChangePanel = ChangePanel;
        initComponents();

        String newReceipt = Payment.generateNewReceiptID();
        ReceiptNo.setText(newReceipt);

        Date.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        POID.setText(POId);
        ItemID.setText(itemId);
        ItemName.setText(itemName);
        Quantity.setText(quantity);
        Supplier.setText(supplier);
        ReferenceNumber.setText(referenceNo);
        PaymentMethod.setText(paymentMethod); 
        PreparedBy.setText(Session.getEmployeeID() + " - " + Session.getUsername());

        String unitPriceStr = Payment.getUnitPriceFromInventory(itemId);
        UnitPrice.setText("RM " + unitPriceStr);
        
        TotalPrice.setText(Payment.calculateTotalPrice(quantity, unitPriceStr));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Date_Label = new javax.swing.JLabel();
        ReceiptNo_Label = new javax.swing.JLabel();
        SaveButton = new javax.swing.JButton();
        PaymentReceipt_Label = new javax.swing.JLabel();
        ReferenceNumber_Label = new javax.swing.JLabel();
        ItemName_Label = new javax.swing.JLabel();
        Quantity_Label = new javax.swing.JLabel();
        Supplier_Label = new javax.swing.JLabel();
        PaymentMethod_Label = new javax.swing.JLabel();
        ReceiptNo = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        Quantity = new javax.swing.JLabel();
        ItemID_Label = new javax.swing.JLabel();
        ItemID = new javax.swing.JLabel();
        PaymentMethod = new javax.swing.JLabel();
        ItemName = new javax.swing.JLabel();
        Supplier = new javax.swing.JLabel();
        ReferenceNumber = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        PreparedBy_Label = new javax.swing.JLabel();
        PreparedBy = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ThankYou_Label = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TotaPrice = new javax.swing.JLabel();
        TotalPrice = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        UnitPrice_Label = new javax.swing.JLabel();
        UnitPrice = new javax.swing.JLabel();
        POID_Label = new javax.swing.JLabel();
        POID = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Date_Label.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        Date_Label.setText("Date  ");

        ReceiptNo_Label.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        ReceiptNo_Label.setText("Receipt No. ");

        SaveButton.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        PaymentReceipt_Label.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        PaymentReceipt_Label.setText("Payment Receipt");

        ReferenceNumber_Label.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        ReferenceNumber_Label.setText("Reference Number");

        ItemName_Label.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        ItemName_Label.setText("Item Name");

        Quantity_Label.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        Quantity_Label.setText("Quantity");

        Supplier_Label.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        Supplier_Label.setText("Supplier");

        PaymentMethod_Label.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        PaymentMethod_Label.setText("PaymentMethod");

        ReceiptNo.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        ReceiptNo.setText("1");

        Date.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        Date.setText("2");

        Quantity.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        Quantity.setText("6");

        ItemID_Label.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        ItemID_Label.setText("Item ID");

        ItemID.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        ItemID.setText("4");

        PaymentMethod.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        PaymentMethod.setText("10");

        ItemName.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        ItemName.setText("5");

        Supplier.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        Supplier.setText("9");

        ReferenceNumber.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        ReferenceNumber.setText("11");

        jLabel1.setText("—————————————————————————————————————————");

        PreparedBy_Label.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        PreparedBy_Label.setText("Prepared By");

        PreparedBy.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        PreparedBy.setText("12");

        jLabel3.setText("—————————————————————————————————————————");

        ThankYou_Label.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        ThankYou_Label.setText("Thank you for your payment!");

        jLabel4.setText("—————————————————————————————————————————");

        TotaPrice.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        TotaPrice.setText("Total Price");

        TotalPrice.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        TotalPrice.setText("8");

        jLabel5.setText("—————————————————————————————————————————");

        jLabel8.setText("—————————————————————————————————————————");

        jLabel9.setText("—————————————————————————————————————————");

        UnitPrice_Label.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        UnitPrice_Label.setText("Unit Price");

        UnitPrice.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        UnitPrice.setText("7");

        POID_Label.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        POID_Label.setText("PO ID");

        POID.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        POID.setText("3");

        jLabel10.setText("————————————————");

        jLabel11.setText("————————————————");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(PaymentReceipt_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(222, 222, 222))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(POID_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ItemID_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(ItemName_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(68, 68, 68)
                        .addComponent(Quantity_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(33, 33, 33)
                        .addComponent(UnitPrice_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(POID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ItemName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(73, 73, 73)
                                .addComponent(Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(UnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(TotaPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8))))
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(169, 169, 169)
                                        .addComponent(ThankYou_Label)))
                                .addGap(8, 8, 8)))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(PreparedBy_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Date_Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Supplier_Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ReceiptNo_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(ReceiptNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(46, 46, 46)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(PaymentMethod_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(ReferenceNumber_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(ReferenceNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                            .addComponent(PaymentMethod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(PreparedBy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Supplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SaveButton)
                .addGap(251, 251, 251))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PaymentReceipt_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ReceiptNo_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ReceiptNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Date_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PaymentMethod_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(PaymentMethod)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ReferenceNumber_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ReferenceNumber))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Supplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Supplier_Label))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PreparedBy, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(PreparedBy_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ItemName_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(ItemID_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Quantity_Label)
                    .addComponent(UnitPrice_Label)
                    .addComponent(POID_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(UnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ItemID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ItemName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(POID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TotaPrice)
                    .addComponent(TotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ThankYou_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        Quantity.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents
      
    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        payment.Payment(
            ReceiptNo.getText(),
            Date.getText(),
            POID.getText(),
            ItemID.getText(),
            ItemName.getText(),
            Quantity.getText(),
            UnitPrice.getText(),
            TotalPrice.getText(),
            Supplier.getText(),
            PaymentMethod.getText(),
            ReferenceNumber.getText(),
            PreparedBy.getText()
        );

        if (payment.saveReceipt(this)) {
            ChangePanel.removeAll();
            ChangePanel.setLayout(new BorderLayout());
            ChangePanel.add(new Finance_VerifyInventory(ChangePanel));
            ChangePanel.revalidate();
            ChangePanel.repaint();
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date;
    private javax.swing.JLabel Date_Label;
    private javax.swing.JLabel ItemID;
    private javax.swing.JLabel ItemID_Label;
    private javax.swing.JLabel ItemName;
    private javax.swing.JLabel ItemName_Label;
    private javax.swing.JLabel POID;
    private javax.swing.JLabel POID_Label;
    private javax.swing.JLabel PaymentMethod;
    private javax.swing.JLabel PaymentMethod_Label;
    private javax.swing.JLabel PaymentReceipt_Label;
    private javax.swing.JLabel PreparedBy;
    private javax.swing.JLabel PreparedBy_Label;
    private javax.swing.JLabel Quantity;
    private javax.swing.JLabel Quantity_Label;
    private javax.swing.JLabel ReceiptNo;
    private javax.swing.JLabel ReceiptNo_Label;
    private javax.swing.JLabel ReferenceNumber;
    private javax.swing.JLabel ReferenceNumber_Label;
    private javax.swing.JButton SaveButton;
    private javax.swing.JLabel Supplier;
    private javax.swing.JLabel Supplier_Label;
    private javax.swing.JLabel ThankYou_Label;
    private javax.swing.JLabel TotaPrice;
    private javax.swing.JLabel TotalPrice;
    private javax.swing.JLabel UnitPrice;
    private javax.swing.JLabel UnitPrice_Label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}