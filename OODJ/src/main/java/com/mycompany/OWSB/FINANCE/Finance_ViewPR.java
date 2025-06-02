/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package com.mycompany.OWSB.FINANCE;
import com.mycompany.OWSB.SALES.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Thong Hui Nan
 */

public class Finance_ViewPR extends javax.swing.JPanel {

    public Finance_ViewPR(javax.swing.JPanel ChangePanel) {
        initComponents();
        loadPRListFromFile();
        setStatusColorRenderer();
    }
    
    private void loadPRListFromFile() {
        String[] columns = {"PR ID", "Item", "Qty", "Supplier", "Status", "Raised By", "Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Map<String, String[]> itemCodeToPRMap = new HashMap<>();
        java.util.List<Items> items = Sales_AddItem.viewItemsInFile();
        for (Items item : items) {
            String prItemCode = item.getItemCode();
            String[] itemDetail = {item.getItemCode(), item.getItemName()};
            itemCodeToPRMap.put(prItemCode, itemDetail);
        }
        
        Map<String, java.util.List<String>> itemCodeToSuppliersMap = new HashMap<>();
        java.util.List<Suppliers> suppliers = Sales_Supplier.viewSuppliersInFile();
        for (Suppliers supplier : suppliers){
            String suppliedItemCode = supplier.getItemSupplied().split(":")[0].trim();
            String supplierInfo = supplier.getSupplierID() + " - " + supplier.getSupplierName();
            itemCodeToSuppliersMap.computeIfAbsent(suppliedItemCode, k -> new ArrayList<>()).add(supplierInfo);
        }
        
        Map<String, String> empMap = loadAllUsers(); 
        for (PR pr : PR.viewPRsInFile()) {
            String[] itemDetail = itemCodeToPRMap.getOrDefault(pr.getItemCode(), new String[]{});
            String item = (itemDetail.length >= 2 ? itemDetail[0] + " - " + itemDetail[1] : "");

            java.util.List<String> suppliersList = itemCodeToSuppliersMap.getOrDefault(pr.getItemCode(), new ArrayList<>());
            String supplierInfo = suppliersList.isEmpty() ? "N/A" : "<html>" + String.join("<br>", suppliersList) + "</html>";

            String empID = pr.getRaisedBy();
            String username = empMap.getOrDefault(empID, "Unknown");
            String raisedBy = empID + " - " + username;
            
            model.addRow(new Object[] {
                pr.getPRID(),
                item,
                pr.getQuantity(),
                supplierInfo,
                pr.getStatus(),
                raisedBy,
                pr.getDate(),
            });
        }

        PR_Table.setModel(model);
        PR_Table.setFont(new Font("Georgia", Font.PLAIN, 10));
        PR_Table.setRowHeight(80);
        PR_Table.getTableHeader().setFont(new Font("Georgia", Font.BOLD, 10));

        int[] columnWidths = {60, 80, 40, 250, 110, 120, 100};
        for (int i = 0; i < columnWidths.length; i++) {
            PR_Table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }
    }
    
    public static Map<String, String> loadAllUsers() {
        Map<String, String> users = new HashMap<>();
        File file = new File("login.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String empID = parts[0].trim();
                    String username = parts[1].trim();
                    users.put(empID, username);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
    
    private void setStatusColorRenderer() {
        PR_Table.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = value != null ? value.toString().toLowerCase() : "";

                if (!isSelected) {
                    switch (status) {
                        case "approved" -> c.setBackground(new Color(144, 238, 144)); // light green
                        case "disclaimed" -> c.setBackground(new Color(255, 182, 193)); // light pink
                        case "pending" -> c.setBackground(new Color(173, 216, 230)); // light blue
                        default -> c.setBackground(Color.WHITE);
                    }
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(table.getSelectionBackground());
                    c.setForeground(table.getSelectionForeground());
                }
                return c;
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PRList_Label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PR_Table = new javax.swing.JTable();
        search = new javax.swing.JLabel();
        btnSearch = new javax.swing.JTextField();
        SearchImage = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));

        PRList_Label.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        PRList_Label.setText("Purchase Requisitions Lists");

        PR_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(PR_Table);

        search.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        search.setText("Search");

        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        SearchImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Search.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(PRList_Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SearchImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PRList_Label)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search))
                        .addComponent(SearchImage, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String query = btnSearch.getText().trim();  
        DefaultTableModel model = (DefaultTableModel) PR_Table.getModel(); 

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        PR_Table.setRowSorter(sorter);

        if (query.length() == 0) {
            sorter.setRowFilter(null);  
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));  
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PRList_Label;
    private javax.swing.JTable PR_Table;
    private javax.swing.JLabel SearchImage;
    private javax.swing.JTextField btnSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel search;
    // End of variables declaration//GEN-END:variables
}