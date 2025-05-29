/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.OWSB.SALES;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xiaochingloh
 */
public class Sales_Supplier extends javax.swing.JPanel {
    private javax.swing.JPanel ChangePanel;
    private DefaultTableModel model = new DefaultTableModel();
    private String[] columnName = {"ID", "NAME", "CONTACT PERSON", "PHONE", "EMAIL", "ADDRESS", "ITEM SUPPLIED", "ACTION"};
    
    /**
     * Creates new form Sales_Supplier
     */
    public Sales_Supplier(javax.swing.JPanel ChangePanel) {
        model.setColumnIdentifiers(columnName);
        initComponents();
        this.ChangePanel = ChangePanel;
        for (int i = 0; i < supplierTable.getColumnCount() - 1; i++) {
            supplierTable.getColumnModel().getColumn(i).setPreferredWidth(110);
            supplierTable.setRowHeight(30);
        }
        supplierTable.getColumnModel().getColumn(supplierTable.getColumnCount() - 3).setPreferredWidth(200);
        supplierTable.getColumnModel().getColumn(supplierTable.getColumnCount() - 2).setPreferredWidth(180);
        supplierTable.getColumnModel().getColumn(supplierTable.getColumnCount() - 1).setPreferredWidth(120);
        supplierTable.getColumnModel().getColumn(supplierTable.getColumnCount() - 6).setPreferredWidth(150);
        List<Suppliers> data = Suppliers.viewSuppliersInFile();
        for (Suppliers supplier : data) {
            model.addRow(new Object[]{
                supplier.getSupplierID(),
                supplier.getSupplierName(),
                supplier.getContactPerson(),
                supplier.getPhone(),
                supplier.getEmail(),
                supplier.getAddress(),
                supplier.getItemSupplied(),
                "Edit/Delete"
            });
        }
        
        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no suppliers currently!");
        }
        
        supplierTable.getColumnModel().getColumn(supplierTable.getColumnCount() - 1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                JButton button = new JButton(value != null ? value.toString() : "Edit/Delete");
                return button;
            }
        });
        
        supplierTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = supplierTable.rowAtPoint(e.getPoint());
                int col = supplierTable.columnAtPoint(e.getPoint());
                if (col == supplierTable.getColumnCount() - 1) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem edit = new JMenuItem("Edit");
                    JMenuItem delete = new JMenuItem("Delete");

                    edit.addActionListener(ae -> {
                        int selectedRow = supplierTable.getSelectedRow();
                        if (selectedRow >= 0){
                            String supplierID = supplierTable.getValueAt(selectedRow, 0).toString();
                            System.out.println("Sales Item: " + supplierID);
                            Sales_EditSupplier edit_supplier = new Sales_EditSupplier(supplierID, ChangePanel);
                            ChangePanel.removeAll();
                            ChangePanel.setLayout(new BorderLayout());
                            ChangePanel.add(edit_supplier, BorderLayout.CENTER);
                            ChangePanel.revalidate();
                            ChangePanel.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Please select an item to edit!");
                        }

                    });
                    
                    delete.addActionListener(ae ->{
                        int selectedRow = supplierTable.getSelectedRow();
                        if (selectedRow >= 0) {
                            String supplierID = supplierTable.getValueAt(selectedRow, 0).toString();
                            String supplierName = supplierTable.getValueAt(selectedRow, 1).toString();
                            
                            int confirm = JOptionPane.showConfirmDialog(
                                null,
                                "Are you sure you want to delete supplier: " + supplierName + "?",
                                "Confirm Deletition",
                                JOptionPane.YES_NO_OPTION
                            );
                            
                            if(confirm == JOptionPane.YES_OPTION){
                                Suppliers.deleteSuppliersInFile(supplierID);
                                ((DefaultTableModel) supplierTable.getModel()).removeRow(selectedRow);
                                JOptionPane.showMessageDialog(null, "Supplier ID: " + supplierID + ", Supplier Name: " + supplierName + "\nDeleted!");
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        supplierTable = new javax.swing.JTable();
        title = new javax.swing.JLabel();
        addNew = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        supplierTable.setModel(model);
        supplierTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(supplierTable);

        title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        title.setText("SUPPLIERS");

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
        Sales_AddSupplier add_supplier = new Sales_AddSupplier(ChangePanel);
        
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(add_supplier, BorderLayout.CENTER);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable supplierTable;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
