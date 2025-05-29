/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.OWSB.SALES;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
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
 * @author xiaochingloh
 */
public class Sales_Item extends javax.swing.JPanel {
    private javax.swing.JPanel ChangePanel;
    private DefaultTableModel model = new DefaultTableModel();
    private String[] columnName = {"ID", "NAME", "SUPPLIER", "CATEGORY", "UNIT PRICE(RM)", "DESCRIPTION", "REORDER ALERT STATUS", "ACTION"};
    
    /**
     * Creates new form Sales_Item
     */
    public Sales_Item(javax.swing.JPanel ChangePanel) {
        model.setColumnIdentifiers(columnName);
        initComponents();
        //If low stock, show cell in red
        itemTable = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                
                int alertCol = 6; 

                if (!isRowSelected(row)) {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                
                //If Low Stock, color red
                if (column == alertCol) {
                    Object value = getValueAt(row, column);
                    if (value != null && value.toString().equalsIgnoreCase("Low Stock")) {
                        c.setBackground(Color.RED);
                        c.setForeground(Color.WHITE);
                    }
                }
                
                return c;
            }
        };
        itemTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(itemTable);
        this.ChangePanel = ChangePanel;
        for (int i = 0; i < itemTable.getColumnCount() - 1; i++) {
            itemTable.getColumnModel().getColumn(i).setPreferredWidth(110);
            itemTable.setRowHeight(30);
        }
        itemTable.getColumnModel().getColumn(itemTable.getColumnCount() - 3).setPreferredWidth(200);
        itemTable.getColumnModel().getColumn(itemTable.getColumnCount() - 2).setPreferredWidth(130);
        itemTable.getColumnModel().getColumn(itemTable.getColumnCount() - 1).setPreferredWidth(120);
        List<Items> data = Items.viewItemsInFile();
        System.out.println(data);
        //Map supplier.txt(FK) itemCode with inventory.txt itemCode(PK)
        Map<String, List<String>> itemCodeToSuppliersMap = new HashMap<>();
        List<Suppliers> suppliers = Suppliers.viewSuppliersInFile();
        for (Suppliers supplier : suppliers){
            String suppliedItemCode = supplier.getItemSupplied().split(":")[0].trim();
            String supplierInfo = supplier.getSupplierID() + "-" + supplier.getSupplierName();
            itemCodeToSuppliersMap.computeIfAbsent(suppliedItemCode, k -> new ArrayList<>()).add(supplierInfo);
        }
        for(Items item : data){
            List<String> suppliersList = itemCodeToSuppliersMap.getOrDefault(item.getItemCode(), new ArrayList<>());
            String supplierInfo = suppliersList.isEmpty() ? "N/A" : "<html>" + String.join("<br>", suppliersList) + "</html>";
            model.addRow(new Object[]{
                item.getItemCode(),
                item.getItemName(),
                supplierInfo,
                item.getCategory(),
                item.getUnitPrice(),
                item.getDescription(),
                item.getReorderStatus(),
                "Edit/Delete"
            });
        }
        
        adjustRowHeights();

        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no items currently!");
        }
        
        itemTable.getColumnModel().getColumn(itemTable.getColumnCount() - 1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                JButton button = new JButton(value != null ? value.toString() : "Edit/Delete");
                return button;
            }
        });
        
        itemTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = itemTable.rowAtPoint(e.getPoint());
                int col = itemTable.columnAtPoint(e.getPoint());
                if (col == itemTable.getColumnCount() - 1) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem edit = new JMenuItem("Edit");
                    JMenuItem delete = new JMenuItem("Delete");

                    edit.addActionListener(ae -> {
                        int selectedRow = itemTable.getSelectedRow();
                        if (selectedRow >= 0){
                            String itemCode = itemTable.getValueAt(selectedRow, 0).toString();
                            System.out.println("Sales Item: " + itemCode);
                            Sales_EditItem edit_item = new Sales_EditItem(itemCode, ChangePanel);
                            ChangePanel.removeAll();
                            ChangePanel.setLayout(new BorderLayout());
                            ChangePanel.add(edit_item, BorderLayout.CENTER);
                            ChangePanel.revalidate();
                            ChangePanel.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Please select an item to edit!");
                        }

                    });
                    
                    delete.addActionListener(ae ->{
                        int selectedRow = itemTable.getSelectedRow();
                        if (selectedRow >= 0) {
                            String itemCode = itemTable.getValueAt(selectedRow, 0).toString();
                            String itemName = itemTable.getValueAt(selectedRow, 1).toString();
                            
                            int confirm = JOptionPane.showConfirmDialog(
                                null,
                                "Are you sure you want to delete item: " + itemName + "?",
                                "Confirm Deletition",
                                JOptionPane.YES_NO_OPTION
                            );
                            
                            if(confirm == JOptionPane.YES_OPTION){
                                Items.deleteItemsInFile(itemCode);
                                ((DefaultTableModel) itemTable.getModel()).removeRow(selectedRow);
                                JOptionPane.showMessageDialog(null, "ItemID: " + itemCode + ", Item Name: " + itemName + "\nDeleted!");
                            }
                        }
                    });

                    popup.add(edit);
                    popup.add(delete);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

    }
    
    private void adjustRowHeights() {
        int supplierColumn = 2; 

        for (int row = 0; row < itemTable.getRowCount(); row++) {
            TableCellRenderer renderer = itemTable.getCellRenderer(row, supplierColumn);
            Component comp = itemTable.prepareRenderer(renderer, row, supplierColumn);
            int height = comp.getPreferredSize().height + 10;

            if (height > itemTable.getRowHeight()) {
                itemTable.setRowHeight(row, height);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();
        title = new javax.swing.JLabel();
        addNew = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        itemTable.setModel(model);
        itemTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        itemTable.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(itemTable);

        title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        title.setText("ITEMS");

        addNew.setText("Add New");
        addNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewActionPerformed(evt);
            }
        });

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

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
                .addComponent(back)
                .addGap(18, 18, 18)
                .addComponent(addNew)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(addNew)
                    .addComponent(title)
                    .addComponent(back))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewActionPerformed
        Sales_AddItem add_item = new Sales_AddItem(ChangePanel);
        
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(add_item, BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_addNewActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        Sales_ItemSupplier is = new Sales_ItemSupplier(ChangePanel);
        
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(is, BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_backActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNew;
    private javax.swing.JButton back;
    private javax.swing.JTable itemTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
