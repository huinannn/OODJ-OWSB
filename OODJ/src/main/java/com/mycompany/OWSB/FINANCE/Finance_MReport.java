/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package com.mycompany.OWSB.FINANCE;
import com.mycompany.OWSB.ADMIN.Session;
import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDate;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.*;

/**
 *
 * @author Thong Hui Nan
 */

public class Finance_MReport extends javax.swing.JPanel {
    private final javax.swing.JPanel ChangePanel;
    private FinancialReport fReport; 
    
    public Finance_MReport(JPanel ChangePanel, String year, String month, List<FinancialReport.SalesRecord> sales, List<FinancialReport.ReceiptRecord> receipts) {this.ChangePanel = ChangePanel;
       initComponents();

       setYearAndMonth(year, month);
       setupSalesTable(sales);
       setupPaymentTable(receipts);
       calculateAndDisplayProfit(sales, receipts);
       prepareFinancialReport(year, month);
    }   

    private void setYearAndMonth(String year, String month) {
        Year.setText(year);
        Month.setText(month);
    }

    private void setupSalesTable(List<FinancialReport.SalesRecord> sales) {
        S_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Item Code", "Item Name", "Quantity Sold", "Total Price (RM)"}
        ));

        DefaultTableModel salesModel = (DefaultTableModel) S_Table.getModel();
        salesModel.setRowCount(0); 
        for (FinancialReport.SalesRecord s : sales) {
            salesModel.addRow(new Object[]{s.itemCode, s.itemName, s.quantitySold, String.format("%.2f", s.totalPrice)});
        }
    }

    private void setupPaymentTable(List<FinancialReport.ReceiptRecord> receipts) {
        P_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Item Code", "Item Name", "Quantity", "Total Price (RM)"}
        ));
        
        DefaultTableModel purchaseModel = (DefaultTableModel) P_Table.getModel();
        purchaseModel.setRowCount(0); 
        for (FinancialReport.ReceiptRecord r : receipts) {
            purchaseModel.addRow(new Object[]{r.itemCode, r.itemName, r.quantity, String.format("%.2f", r.totalPrice)});
        }
    }

    private void calculateAndDisplayProfit(List<FinancialReport.SalesRecord> sales, List<FinancialReport.ReceiptRecord> receipts) {
        double totalSales = sales.stream().mapToDouble(s -> s.totalPrice).sum();
        TotalSales.setText(String.format("RM %.2f", totalSales));

        double totalCost = receipts.stream().mapToDouble(r -> r.totalPrice).sum();
        TotalPayment.setText(String.format("RM %.2f", totalCost));

        double profit = totalSales - totalCost;
        Profit.setText(String.format("RM %.2f", profit));

        if (profit < 0) {
            Label.setText("Monthly Loss:");
            Label.setForeground(Color.RED);
        } else {
            Label.setText("Monthly Profit:");
            Label.setForeground(Color.GREEN);
        }
    }

    private void prepareFinancialReport(String year, String month) {
        Map<String, Double> salesMap = new HashMap<>();
        Map<String, Double> paymentMap = new HashMap<>();

        salesMap.put(month, Double.valueOf(TotalSales.getText().replace("RM ", "")));
        paymentMap.put(month, Double.valueOf(TotalPayment.getText().replace("RM ", "")));

        fReport = new FinancialReport();
        fReport.setReportId(FinancialReport.generateNewFinanceReportID());
        fReport.setYear(year);
        fReport.setDate(LocalDate.now());
        fReport.setSalesMap(salesMap);
        fReport.setPaymentMap(paymentMap);
        fReport.setPreparedBy(Session.getEmployeeID() + " - " + Session.getUsername());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Finance_Report_Label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TotalSales = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();
        Month = new javax.swing.JLabel();
        TotalSales_Label = new javax.swing.JLabel();
        Month_Label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        S_Table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        P_Table = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TotalPayment = new javax.swing.JLabel();
        TotalPayment_Label = new javax.swing.JLabel();
        Label = new javax.swing.JLabel();
        Profit = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Year = new javax.swing.JLabel();
        SaveButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Finance_Report_Label.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Finance_Report_Label.setText("Monthly Financial Report For");

        jLabel1.setText("—————————————————————————————————————————");

        TotalSales.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        TotalSales.setText("2");

        jLabel5.setText("—————————————————————————————————————————");

        BackButton.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        Month.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        Month.setText("1");

        TotalSales_Label.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        TotalSales_Label.setText("Total Monthy Sales:");

        Month_Label.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        Month_Label.setText("Month:");

        S_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(S_Table);

        jLabel2.setText("—————————————————————————————————————————");

        jLabel3.setText("—————————————————————————————————————————");

        P_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(P_Table);

        jLabel4.setText("——————————————");

        jLabel7.setText("—————————————————————————————————————————");

        TotalPayment.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        TotalPayment.setText("3");

        TotalPayment_Label.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        TotalPayment_Label.setText("Total Monthy Payment:");

        Label.setFont(new java.awt.Font("Adobe Devanagari", 1, 14)); // NOI18N
        Label.setText("Profit / Loss:");

        Profit.setFont(new java.awt.Font("Adobe Devanagari", 0, 14)); // NOI18N
        Profit.setText("4");

        jLabel8.setText("—————————————————————————————————————————");

        Year.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Year.setText("xx Year");

        SaveButton.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(Month_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Month, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(259, 259, 259))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(264, 264, 264)
                                .addComponent(TotalSales_Label)
                                .addGap(18, 18, 18)
                                .addComponent(TotalSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(251, 251, 251)
                                        .addComponent(TotalPayment_Label)
                                        .addGap(18, 18, 18)
                                        .addComponent(TotalPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(321, 321, 321)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Profit, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(3, 3, 3))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(48, 48, 48))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(BackButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44)
                .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(164, 164, 164))
            .addGroup(layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(Finance_Report_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Year)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Finance_Report_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Year, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Month_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Month, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalSales_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TotalSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalPayment_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TotalPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Profit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BackButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(new Finance_MChooseDate(ChangePanel), BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_BackButtonActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        fReport.saveToMonthlyFile();

        JOptionPane.showMessageDialog(this, "Monthly Financial Report saved successfully!", 
                                      "Success", JOptionPane.INFORMATION_MESSAGE);

        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(new Finance_FinancialReport(ChangePanel), BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_SaveButtonActionPerformed
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JLabel Finance_Report_Label;
    private javax.swing.JLabel Label;
    private javax.swing.JLabel Month;
    private javax.swing.JLabel Month_Label;
    private javax.swing.JTable P_Table;
    private javax.swing.JLabel Profit;
    private javax.swing.JTable S_Table;
    private javax.swing.JButton SaveButton;
    private javax.swing.JLabel TotalPayment;
    private javax.swing.JLabel TotalPayment_Label;
    private javax.swing.JLabel TotalSales;
    private javax.swing.JLabel TotalSales_Label;
    private javax.swing.JLabel Year;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}