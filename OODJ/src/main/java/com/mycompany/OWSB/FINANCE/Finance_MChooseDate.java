/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package com.mycompany.OWSB.FINANCE;
import java.awt.*;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Thong Hui Nan
 */

public class Finance_MChooseDate extends javax.swing.JPanel {
    private final javax.swing.JPanel ChangePanel;
    
    public Finance_MChooseDate(javax.swing.JPanel ChangePanel) {
        this.ChangePanel = ChangePanel;
        initComponents();
        
        for (FinancialReport.MonthEnum month : FinancialReport.MonthEnum.values()) {
            Month_Dropdown.addItem(month.name());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Selection_Label = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();
        GenerateButton = new javax.swing.JButton();
        Year_Label = new javax.swing.JLabel();
        Month_Label = new javax.swing.JLabel();
        Month_Dropdown = new javax.swing.JComboBox<>();
        Year_TextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        Selection_Label.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        Selection_Label.setText("Select the year and month");

        BackButton.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        GenerateButton.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        GenerateButton.setText("Generate");
        GenerateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateButtonActionPerformed(evt);
            }
        });

        Year_Label.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        Year_Label.setText("Year");

        Month_Label.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        Month_Label.setText("Month");

        Month_Dropdown.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Month_Dropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Month_DropdownActionPerformed(evt);
            }
        });

        Year_TextField.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(Selection_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Year_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(Month_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(49, 49, 49))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(BackButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(GenerateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(60, 60, 60))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Year_TextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Month_Dropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(122, 122, 122))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(Selection_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Year_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Year_TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Month_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Month_Dropdown, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BackButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(GenerateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(145, 145, 145))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(new Finance_FinancialReport(ChangePanel), BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_BackButtonActionPerformed

    private void GenerateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateButtonActionPerformed
        String selectedMonth = Month_Dropdown.getSelectedItem() != null ? Month_Dropdown.getSelectedItem().toString() : "";
        String yearInput = Year_TextField.getText().trim();

        if (selectedMonth.isEmpty() || yearInput.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please select a month and enter a year.",
                "Input Required",
                JOptionPane.INFORMATION_MESSAGE);
            return; 
        }

        FinancialReport report = new FinancialReport();
        List<FinancialReport.SalesRecord> sales = report.getSalesForMonth(selectedMonth, yearInput);
        List<FinancialReport.ReceiptRecord> receipts = report.getReceiptForMonth(selectedMonth, yearInput);

        if (sales.isEmpty() && receipts.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No sales or purchase records found for the selected month and year.", 
                "Information", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            Finance_MReport reportPanel = new Finance_MReport(ChangePanel, yearInput, selectedMonth, sales, receipts);
            ChangePanel.removeAll();
            ChangePanel.add(reportPanel);
            ChangePanel.revalidate();
            ChangePanel.repaint();
        }
    }//GEN-LAST:event_GenerateButtonActionPerformed

    private void Month_DropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Month_DropdownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Month_DropdownActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton GenerateButton;
    private javax.swing.JComboBox<String> Month_Dropdown;
    private javax.swing.JLabel Month_Label;
    private javax.swing.JLabel Selection_Label;
    private javax.swing.JLabel Year_Label;
    private javax.swing.JTextField Year_TextField;
    // End of variables declaration//GEN-END:variables
}