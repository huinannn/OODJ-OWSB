/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package com.mycompany.OWSB.FINANCE;
import java.awt.*;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Thong Hui Nan
 */

public class Finance_YChooseDate extends javax.swing.JPanel {
    private final javax.swing.JPanel ChangePanel;
    
    public Finance_YChooseDate(javax.swing.JPanel ChangePanel) {
        this.ChangePanel = ChangePanel;
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Selection_Label = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();
        GenerateButton = new javax.swing.JButton();
        Year_Label = new javax.swing.JLabel();
        Year_TextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        Selection_Label.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        Selection_Label.setText("Enter the year");

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

        Year_TextField.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Year_Label)
                        .addGap(103, 103, 103)
                        .addComponent(Year_TextField))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(GenerateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(Selection_Label)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(130, 130, 130))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(Selection_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 167, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(GenerateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(207, 207, 207))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Year_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Year_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
        String yearInput = Year_TextField.getText().trim();

        if (yearInput.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter the year.",
                "Input Required",
                JOptionPane.INFORMATION_MESSAGE);
            return; 
        }

        FinancialReport report = new FinancialReport();
        Map<String, Double> YearlySales = report.getMonthlySalesForYear(yearInput);
        Map<String, Double> YearlyPayments = report.getMonthlyPaymentsForYear(yearInput);

        boolean hasData = YearlySales.values().stream().anyMatch(s -> s > 0)
                  || YearlyPayments.values().stream().anyMatch(p -> p > 0);

        if (!hasData) {
            JOptionPane.showMessageDialog(this, 
                "No sales or payment records found for the selected year.", 
                "Information", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            Finance_YReport reportPanel = new Finance_YReport(ChangePanel, yearInput, YearlySales, YearlyPayments);
            ChangePanel.removeAll();
            ChangePanel.add(reportPanel);
            ChangePanel.revalidate();
            ChangePanel.repaint();
        }
    }//GEN-LAST:event_GenerateButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton GenerateButton;
    private javax.swing.JLabel Selection_Label;
    private javax.swing.JLabel Year_Label;
    private javax.swing.JTextField Year_TextField;
    // End of variables declaration//GEN-END:variables
}