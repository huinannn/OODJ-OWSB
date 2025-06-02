/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package com.mycompany.OWSB.FINANCE;

import com.mycompany.OWSB.SALES.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Thong Hui Nan
 */

public class Finance_VerifyInventory extends javax.swing.JPanel {
    private final javax.swing.JPanel ChangePanel;
    
    public Finance_VerifyInventory(javax.swing.JPanel ChangePanel) {
        this.ChangePanel = ChangePanel;
        initComponents();
        loadInventoryFromFile();
        setStatusColorRenderer();
    }
    
    private void loadInventoryFromFile() {
        List<String[]> stockList = loadStockList();
        DefaultTableModel model = createInventoryTableModel(stockList);
        setupInventoryTable(model);
        setActionButtonRendererAndEditor();
    }

    private List<String[]> loadStockList() {
        List<String[]> stockList = new ArrayList<>();
        File file = new File("Stock.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "stock.txt not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return stockList;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {  
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(";");
                if (parts.length >= 12) { 
                    stockList.add(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    private DefaultTableModel createInventoryTableModel(List<String[]> stockList) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 12;
            }
        };

        model.setColumnIdentifiers(new String[]{
            "SID", "POID", "Item", "Qty", "Price", "Supplier", "Arrival",
            "PQty", "CQty", "Approval", "ADate", "PStatus", "Action"
        });

        Map<String, String> itemIdToName = loadItemsMap();
        Map<String, String> supplierIdToName = loadSuppliersMap();

        for (String[] stock : stockList) {
            String stockID = stock[0];
            String POID = stock[1];
            String itemID = stock[2];
            String itemName = itemIdToName.getOrDefault(itemID, "Unknown Item");
            String itemDisplay = itemID + " - " + itemName;

            String qty = stock[4];
            String price = String.format("%.2f", Double.valueOf(stock[5]));

            String supplierRaw = stock[6];
            String supplierID = supplierRaw.split(":")[0].trim();
            String supplierName = supplierIdToName.getOrDefault(supplierID, "Unknown Supplier");
            String supplierDisplay = supplierID + " - " + supplierName;

            String arrival = stock[7];
            String pQty = stock[8];
            String cQty = stock[9];
            String approval = stock[10];
            String aDate = stock[11];
            String pStatus = stock[12];

            model.addRow(new Object[]{
                stockID,
                POID,
                itemDisplay,
                qty,
                price,
                supplierDisplay,
                arrival,
                pQty,
                cQty,
                approval,
                aDate,
                pStatus,
                "Approve"
            });
        }
        return model;
    }

    private Map<String, String> loadItemsMap() {
        Map<String, String> map = new HashMap<>();
        java.util.List<Items> items = Sales_AddItem.viewItemsInFile();
        for (Items item : items) {
            map.put(item.getItemCode(), item.getItemName());
        }
        return map;
    }

    private Map<String, String> loadSuppliersMap() {
        Map<String, String> map = new HashMap<>();
        java.util.List<Suppliers> suppliers = Sales_Supplier.viewSuppliersInFile();
        for (Suppliers supplier : suppliers) {
            map.put(supplier.getSupplierID(), supplier.getSupplierName());
        }
        return map;
    }

    private void setupInventoryTable(DefaultTableModel model) {
        Inventory_Table.setModel(model);
        Inventory_Table.setFont(new Font("Georgia", Font.PLAIN, 10));
        Inventory_Table.setRowHeight(50);
        Inventory_Table.getTableHeader().setFont(new Font("Georgia", Font.BOLD, 10));

        int[] widths = {55, 55, 82, 36, 65, 88, 60, 36, 36, 95, 58, 45, 62};
        for (int i = 0; i < widths.length; i++) {
            Inventory_Table.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }
    }

    private void setActionButtonRendererAndEditor() {
        Inventory_Table.getColumnModel().getColumn(12).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                JButton button = new JButton("Pay");
                button.setFont(new Font("Georgia", Font.PLAIN, 9));

                String approval = table.getValueAt(row, 9).toString();
                String pStatus = table.getValueAt(row, 11).toString();
                boolean canPay = approval.equalsIgnoreCase("Approved") && pStatus.equalsIgnoreCase("Pending");

                button.setEnabled(true);
                button.setForeground(canPay ? Color.BLACK : Color.BLACK); 

                return button;
            }
        });

        Inventory_Table.getColumnModel().getColumn(12).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private final JButton button = new JButton("Pay");
            private int currentRow;

            {
                button.addActionListener(e -> {
                    String approval = Inventory_Table.getValueAt(currentRow, 9).toString();
                    String pStatus = Inventory_Table.getValueAt(currentRow, 11).toString();

                    if (pStatus.equalsIgnoreCase("Paid")) {
                        JOptionPane.showMessageDialog(Inventory_Table, "Payment has already been made.");
                        fireEditingCanceled();
                        return;
                    }

                    if (!(approval.equalsIgnoreCase("Approved") && pStatus.equalsIgnoreCase("Unpaid"))) {
                        JOptionPane.showMessageDialog(Inventory_Table,
                                "Cannot make payment until approval is granted and payment status is unpaid.");
                        fireEditingCanceled();
                        return;
                    }

                    String stockId = Inventory_Table.getValueAt(currentRow, 0).toString();
                    System.out.println("Make payment for stock Id: " + stockId);
                    Finance_MakePayment paymentPanel = new Finance_MakePayment(ChangePanel, stockId);
                    ChangePanel.removeAll();
                    ChangePanel.setLayout(new BorderLayout());
                    ChangePanel.add(paymentPanel, BorderLayout.CENTER);
                    ChangePanel.revalidate();
                    ChangePanel.repaint();

                    fireEditingStopped();
                });
            }

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value,
                                                         boolean isSelected, int row, int column) {
                currentRow = row;
                return button;
            }
        });
    }
    
    private void setStatusColorRenderer() {
        TableCellRenderer statusRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = value != null ? value.toString().toLowerCase().trim() : "";

                if (!isSelected) {
                    switch (status) {
                        case "approved", "paid" -> c.setBackground(new Color(144, 238, 144)); 
                        case "disclaimed" -> c.setBackground(new Color(255, 182, 193));      
                        case "pending", "unpaid" -> c.setBackground(new Color(173, 216, 230));         
                        default -> c.setBackground(Color.WHITE);
                    }
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(table.getSelectionBackground());
                    c.setForeground(table.getSelectionForeground());
                }
                return c;
            }
        };

        Inventory_Table.getColumnModel().getColumn(9).setCellRenderer(statusRenderer);  
        Inventory_Table.getColumnModel().getColumn(11).setCellRenderer(statusRenderer); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InventoryList_Label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Inventory_Table = new javax.swing.JTable();
        SearchImage = new javax.swing.JLabel();
        search = new javax.swing.JLabel();
        btnSearch = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        InventoryList_Label.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        InventoryList_Label.setText("Inventory Lists");

        Inventory_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(Inventory_Table);

        SearchImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Search.jpg"))); // NOI18N

        search.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        search.setText("Search");

        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(InventoryList_Label)
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
                    .addComponent(InventoryList_Label)
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
        DefaultTableModel model = (DefaultTableModel) Inventory_Table.getModel();

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        Inventory_Table.setRowSorter(sorter);

        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel InventoryList_Label;
    private javax.swing.JTable Inventory_Table;
    private javax.swing.JLabel SearchImage;
    private javax.swing.JTextField btnSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel search;
    // End of variables declaration//GEN-END:variables
}