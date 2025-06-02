/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package com.mycompany.OWSB.FINANCE;

import java.awt.*;

/**
 *
 * @author Thong Hui Nan
 */

public class Finance_FinancialReport extends javax.swing.JPanel {
    private final javax.swing.JPanel ChangePanel;
    
    public Finance_FinancialReport(javax.swing.JPanel ChangePanel) {
        this.ChangePanel = ChangePanel;
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FinanceReport_Label = new javax.swing.JLabel();
        MontlyButton = new javax.swing.JButton();
        YearlyButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        FinanceReport_Label.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        FinanceReport_Label.setText("Financial Report");

        MontlyButton.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        MontlyButton.setText("Monthly");
        MontlyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MontlyButtonActionPerformed(evt);
            }
        });

        YearlyButton.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        YearlyButton.setText("Yearly");
        YearlyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YearlyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MontlyButton, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addComponent(YearlyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(93, 93, 93))
            .addGroup(layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(FinanceReport_Label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(FinanceReport_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(51, 51, 51)
                .addComponent(YearlyButton, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addGap(66, 66, 66)
                .addComponent(MontlyButton, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addGap(112, 112, 112))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void MontlyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MontlyButtonActionPerformed
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(new Finance_MChooseDate(ChangePanel), BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_MontlyButtonActionPerformed

    private void YearlyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YearlyButtonActionPerformed
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(new Finance_YChooseDate(ChangePanel), BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_YearlyButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FinanceReport_Label;
    private javax.swing.JButton MontlyButton;
    private javax.swing.JButton YearlyButton;
    // End of variables declaration//GEN-END:variables
}