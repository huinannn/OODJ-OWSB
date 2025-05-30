/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.OWSB.SALES;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author TP070386
 */
public class Sales_PR extends javax.swing.JPanel {
    private javax.swing.JPanel ChangePanel;
    private DefaultTableModel model = new DefaultTableModel();
    private String[] columnName = {"ID", "ITEM", "QUANTITY", "SUPPLIER", "STATUS", "RAISED BY", "REQUIRED DATE", "ACTION"};
    /**
     * Creates new form Sales_PR
     */
    public Sales_PR(javax.swing.JPanel ChangePanel) {
        model.setColumnIdentifiers(columnName);
        initComponents();
        this.ChangePanel = ChangePanel;
        for (int i = 0; i < prTable.getColumnCount() - 1; i++){
            prTable.getColumnModel().getColumn(i).setPreferredWidth(110);
            prTable.setRowHeight(30);
        }
        prTable.getColumnModel().getColumn(prTable.getColumnCount() - 5).setPreferredWidth(150);
        prTable.getColumnModel().getColumn(prTable.getColumnCount() - 3).setPreferredWidth(150);
        prTable.getColumnModel().getColumn(prTable.getColumnCount() - 2).setPreferredWidth(130);
        prTable.getColumnModel().getColumn(prTable.getColumnCount() - 1).setPreferredWidth(120);
        prTable.setFont(new java.awt.Font("Georgia", java.awt.Font.PLAIN, 12));
        List<PR> data = PR.viewPRsInFile();
        //Map pr.txt (FK)supplierID with supplier.txt (PK)supplierID
        Map<String, List<String>> PRToSupplierMap = new HashMap<>();
        List<Suppliers> suppliers = Suppliers.viewSuppliersInFile();
        for (Suppliers supplier : suppliers){
            String supplierID = supplier.getSupplierID();
            String supplierInfo = supplier.getSupplierID() + "-" +supplier.getSupplierName();
            PRToSupplierMap.computeIfAbsent(supplierID, k -> new ArrayList<>()).add(supplierInfo);
        }
        for (PR pr : data){
            List<String> suppliersList = PRToSupplierMap.getOrDefault(pr.getSupplierID(), new ArrayList<>());
            String supplierInfo = suppliersList.isEmpty() ? "N/A" : "<html>" + String.join("<br>", suppliersList) + "</html>";
            model.addRow(new Object[]{
                pr.getPRID(),
                pr.getItemCode() + ": " + pr.getItemName(),
                pr.getQuantity(),
                supplierInfo,
                pr.getStatus(),
                pr.getRaisedBy(),
                pr.getDate(),
                "Edit/Delete"
            });
        }
        
        adjustRowHeights();
        
        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no purchase requisitions currently!");
        }
        
        prTable.getColumnModel().getColumn(prTable.getColumnCount() - 1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                JButton button = new JButton(value != null ? value.toString() : "Edit/Delete");
                return button;
            }
        });
        
        prTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = prTable.rowAtPoint(e.getPoint());
                int col = prTable.columnAtPoint(e.getPoint());
                if (col == prTable.getColumnCount() - 1) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem edit = new JMenuItem("Edit");
                    JMenuItem delete = new JMenuItem("Delete");

//                    edit.addActionListener(ae -> {
//                        int selectedRow = prTable.getSelectedRow();
//                        if (selectedRow >= 0){
//                            String itemCode = prTable.getValueAt(selectedRow, 0).toString();
//                            System.out.println("Sales Item: " + itemCode);
//                            Sales_EditItem edit_item = new Sales_EditItem(itemCode, ChangePanel);
//                            ChangePanel.removeAll();
//                            ChangePanel.setLayout(new BorderLayout());
//                            ChangePanel.add(edit_item, BorderLayout.CENTER);
//                            ChangePanel.revalidate();
//                            ChangePanel.repaint();
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Please select an item to edit!");
//                        }
//
//                    });
                    
//                    delete.addActionListener(ae ->{
//                        int selectedRow = prTable.getSelectedRow();
//                        if (selectedRow >= 0) {
//                            String itemCode = prTable.getValueAt(selectedRow, 0).toString();
//                            String itemName = prTable.getValueAt(selectedRow, 1).toString();
//                            
//                            int confirm = JOptionPane.showConfirmDialog(
//                                null,
//                                "Are you sure you want to delete item: " + itemName + "?",
//                                "Confirm Deletition",
//                                JOptionPane.YES_NO_OPTION
//                            );
//                            
//                            if(confirm == JOptionPane.YES_OPTION){
//                                Items.deleteItemsInFile(itemCode);
//                                ((DefaultTableModel) prTable.getModel()).removeRow(selectedRow);
//                                JOptionPane.showMessageDialog(null, "ItemID: " + itemCode + ", Item Name: " + itemName + "\nDeleted!");
//                            }
//                        }
//                    });

                    popup.add(edit);
                    popup.add(delete);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
    
    private void adjustRowHeights() {
        int supplierColumn = 2; 

        for (int row = 0; row < prTable.getRowCount(); row++) {
            TableCellRenderer renderer = prTable.getCellRenderer(row, supplierColumn);
            Component comp = prTable.prepareRenderer(renderer, row, supplierColumn);
            int height = comp.getPreferredSize().height + 10;

            if (height > prTable.getRowHeight()) {
                prTable.setRowHeight(row, height);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        addNew = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        prTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        title.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        title.setText("PURCHASE REQUISITIONS");

        addNew.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        addNew.setText("Add New");
        addNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewActionPerformed(evt);
            }
        });

        prTable.setModel(model);
        prTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(prTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addNew)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(addNew)
                    .addComponent(title))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewActionPerformed
//        Sales_AddSupplier add_supplier = new Sales_AddSupplier(ChangePanel);
//
//        ChangePanel.removeAll();
//        ChangePanel.setLayout(new BorderLayout());
//        ChangePanel.add(add_supplier, BorderLayout.CENTER);
//        ChangePanel.revalidate();
//        ChangePanel.repaint();
    }//GEN-LAST:event_addNewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNew;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable prTable;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
