/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package com.mycompany.OWSB.FINANCE;
import com.mycompany.OWSB.PURCHASE.PO;
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

public class Finance_ViewPO extends javax.swing.JPanel {
    private final javax.swing.JPanel ChangePanel;
    
    public Finance_ViewPO(javax.swing.JPanel ChangePanel) {
        this.ChangePanel = ChangePanel;
        initComponents();
        loadPOListFromFile();
    }
    
    private void loadPOListFromFile() {
        List<PO> poList = loadPOListFromFileToObjects(); 
        DefaultTableModel model = createPOTableModel(poList);
        setupPO_Table(model);
        setStatusColorRenderer();
        setEditButtonFunctionality();
    }

    private DefaultTableModel createPOTableModel(List<PO> poList) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8; 
            }
        };

        model.setColumnIdentifiers(new String[]{
             "PO ID", "Item", "Qty", "Total", "Supplier", "Raised By", "Date", "Status", "Action"
        });
        
        Map<String, String[]> itemCodeToPRMap = new HashMap<>();
        java.util.List<Items> items = Sales_AddItem.viewItemsInFile();
        for (Items item : items) {
            String prItemCode = item.getItemCode();
            String[] itemDetail = {item.getItemCode(), item.getItemName()};
            itemCodeToPRMap.put(prItemCode, itemDetail);
        }

        Map<String, java.util.List<String>> itemCodeToSuppliersMap = new HashMap<>();
        java.util.List<Suppliers> suppliers = Sales_Supplier.viewSuppliersInFile();
        for (Suppliers supplier : suppliers) {
            String suppliedItemCode = supplier.getItemSupplied().split(":")[0].trim();
            String supplierInfo = supplier.getSupplierID() + " - " + supplier.getSupplierName();
            itemCodeToSuppliersMap.computeIfAbsent(suppliedItemCode, k -> new ArrayList<>()).add(supplierInfo);
        }
        
        Map<String, String> empMap = loadAllUsers();

        for (PO po : poList) {
            String itemCode = po.getItemID();
            if (!itemCodeToPRMap.containsKey(itemCode)) continue; 
            
            String[] itemDetails = itemCodeToPRMap.get(itemCode);
            String itemName = itemDetails[1];

            String supplierID = po.getSupplierID();
            String supplierInfo = supplierID;

            for (Suppliers supplier : suppliers) {
                if (supplier.getSupplierID().equals(supplierID)) {
                    supplierInfo = supplierID + " - " + supplier.getSupplierName();
                    break;
                }
            }

            String empID = po.getRaisedBy();
            if (!empMap.containsKey(empID)) continue; 
            String username = empMap.get(empID);
            String raisedBy = empID + " - " + username;

            model.addRow(new Object[]{
                po.getPOID(),
                itemCode + " - " + itemName,
                po.getQuantity(),
                String.format("%.2f", po.getTotalPrice()),
                supplierInfo,
                raisedBy,
                po.getDeliveryDate(),
                po.getStatus(),
                "Edit"
            });
        }
        return model;
    }

    private void setupPO_Table(DefaultTableModel model) {
        PO_Table.setModel(model);
        PO_Table.setFont(new Font("Georgia", Font.PLAIN, 10));
        PO_Table.setRowHeight(50);
        PO_Table.getTableHeader().setFont(new Font("Georgia", Font.BOLD, 10));

        int[] columnWidths = {60, 60, 50, 58, 65, 68, 82, 100, 60};
        for (int i = 0; i < columnWidths.length; i++) {
            PO_Table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }
    }
   
    private void setEditButtonFunctionality() {
        PO_Table.getColumnModel().getColumn(8).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JButton button = new JButton("Edit");
                button.setFont(new Font("Georgia", Font.PLAIN, 10));

                button.setEnabled(true);
                return button;
            }
        });

        PO_Table.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private final JButton button = new JButton("Edit");
            private int currentRow;

            {
                button.addActionListener(e -> {
                    String status = PO_Table.getValueAt(currentRow, 7).toString();
                    if (!status.equalsIgnoreCase("Pending")) {
                        JOptionPane.showMessageDialog(PO_Table, "Only Pending POs can be edited.");
                        fireEditingCanceled();
                        return;
                    }

                    String poID = PO_Table.getValueAt(currentRow, 0).toString();
                    System.out.println("Editing PO ID: " + poID);
                    Finance_EditPO editPanel = new Finance_EditPO(poID, ChangePanel);
                    ChangePanel.removeAll();
                    ChangePanel.setLayout(new BorderLayout());
                    ChangePanel.add(editPanel, BorderLayout.CENTER);
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

    private List<PO> loadPOListFromFileToObjects() {
        List<PO> poList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("PO_Lists.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 9) {
                    PO po = new PO(
                        data[0], data[1], data[2],
                        Integer.parseInt(data[3]),
                        Double.parseDouble(data[4]),
                        data[5], data[6], data[7],
                        PO.PO_Status.fromString(data[8]) 
                    );
                    poList.add(po);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return poList;
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
        PO_Table.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = value != null ? value.toString().toLowerCase() : "";

                if (!isSelected) {
                    switch (status) {
                        case "approved" -> c.setBackground(new Color(144, 238, 144)); 
                        case "disclaimed" -> c.setBackground(new Color(255, 182, 193)); 
                        case "pending" -> c.setBackground(new Color(173, 216, 230)); 
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

        POList_Label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PO_Table = new javax.swing.JTable();
        SearchImage = new javax.swing.JLabel();
        search = new javax.swing.JLabel();
        btnSearch = new javax.swing.JTextField();
        SearchImage4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        POList_Label.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        POList_Label.setText("Purchase Order Lists");

        PO_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(PO_Table);

        search.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        search.setText("Search");

        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        SearchImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Search.jpg"))); // NOI18N

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
                        .addComponent(POList_Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SearchImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchImage4)
                        .addGap(12, 12, 12)
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
                    .addComponent(POList_Label)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(SearchImage4)
                            .addGap(4, 4, 4))
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
        DefaultTableModel model = (DefaultTableModel) PO_Table.getModel();

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        PO_Table.setRowSorter(sorter);

        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel POList_Label;
    private javax.swing.JTable PO_Table;
    private javax.swing.JLabel SearchImage;
    private javax.swing.JLabel SearchImage4;
    private javax.swing.JTextField btnSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel search;
    // End of variables declaration//GEN-END:variables
}