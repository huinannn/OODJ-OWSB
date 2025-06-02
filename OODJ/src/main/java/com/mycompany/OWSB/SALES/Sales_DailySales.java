/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.OWSB.SALES;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

/**
 *
 * @author xiaochingloh
 */
public class Sales_DailySales extends javax.swing.JPanel {
    private javax.swing.JPanel ChangePanel;
    private DefaultTableModel model = new DefaultTableModel();
    private String[] columnName = {"SALESID", "ITEM", "QUANTITY SOLD", "TOTAL AMOUNT(RM)", "DATE OF SALES", "ACTION"};
    
    /**
     * Creates new form Sales_DailySales
     */
    public Sales_DailySales(javax.swing.JPanel ChangePanel) {
        model.setColumnIdentifiers(columnName);
        initComponents();
        dsTable = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        dsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(dsTable);
        this.ChangePanel = ChangePanel;
        for (int i = 0; i < dsTable.getColumnCount() - 1; i++) {
            dsTable.getColumnModel().getColumn(i).setPreferredWidth(110);
            dsTable.setRowHeight(30);
        }
        dsTable.getColumnModel().getColumn(dsTable.getColumnCount() - 5).setPreferredWidth(200);
        dsTable.getColumnModel().getColumn(dsTable.getColumnCount() - 3).setPreferredWidth(150);
        dsTable.getColumnModel().getColumn(dsTable.getColumnCount() - 1).setPreferredWidth(120);
        dsTable.setFont(new java.awt.Font("Georgia", java.awt.Font.PLAIN, 12));
        List<DailySales> data = DailySales.viewDSInFile();
        //Map DailySales.txt itemCode(FK) with inventory.txt itemCode (PK)
        Map<String, String[]> itemCodeToDSMap = new HashMap<>();
        List<Items> items = Sales_AddItem.viewItemsInFile();
        for (Items item : items){
            String dsItemCode = item.getItemCode();
            String[] itemDetail = {item.getItemCode(), item.getItemName()};
            itemCodeToDSMap.put(dsItemCode, itemDetail);
        }
        for (DailySales ds : data){
            String[] itemDetail = itemCodeToDSMap.getOrDefault(ds.getItemCode(), new String[]{});
            String item = (itemDetail.length >= 2 ? itemDetail[0] + " - " + itemDetail[1] : "");
            String totalAmount = String.format("%.2f", ds.calculateTotalSales());
            model.addRow(new Object[]{
                ds.getSalesID(),
                item,
                ds.getQuantitySold(),
                totalAmount,
                ds.getDate(),
                "Edit/Delete"
            });
        }
        
        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no daily sales currently!");
        }
        
        dsTable.getColumnModel().getColumn(dsTable.getColumnCount() - 1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                JButton button = new JButton(value != null ? value.toString() : "Edit/Delete");
                return button;
            }
        });
        
        dsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = dsTable.rowAtPoint(e.getPoint());
                int col = dsTable.columnAtPoint(e.getPoint());
                if (col == dsTable.getColumnCount() - 1) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem edit = new JMenuItem("Edit");
                    JMenuItem delete = new JMenuItem("Delete");

                    edit.addActionListener(ae -> {
                        int selectedRow = dsTable.getSelectedRow();
                        if (selectedRow >= 0){
                            String DSID = dsTable.getValueAt(selectedRow, 0).toString();
                            System.out.println("Sales Item: " + DSID);
                            Sales_EditDailySales edit_ds = new Sales_EditDailySales(DSID, ChangePanel);
                            ChangePanel.removeAll();
                            ChangePanel.setLayout(new BorderLayout());
                            ChangePanel.add(edit_ds, BorderLayout.CENTER);
                            ChangePanel.revalidate();
                            ChangePanel.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Please select an item to edit!");
                        }

                    });
                    
                    delete.addActionListener(ae ->{
                        int selectedRow = dsTable.getSelectedRow();
                        if (selectedRow >= 0) {
                            String DSID = dsTable.getValueAt(selectedRow, 0).toString();
                            String item = dsTable.getValueAt(selectedRow, 1).toString();
                            
                            int confirm = JOptionPane.showConfirmDialog(
                                null,
                                "Are you sure you want to delete Sales that contains item: " + item + "?",
                                "Confirm Deletition",
                                JOptionPane.YES_NO_OPTION
                            );
                            
                            if(confirm == JOptionPane.YES_OPTION){
                                DailySales.deleteDSInFile(DSID);
                                ((DefaultTableModel) dsTable.getModel()).removeRow(selectedRow);
                                JOptionPane.showMessageDialog(null, "Sales ID: " + DSID + ", Item Name: " + item + "\nDeleted!");
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

        title = new javax.swing.JLabel();
        addNew = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dsTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        title.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        title.setText("DAILY ITEM-WISE SALES");

        addNew.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        addNew.setText("Add New");
        addNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewActionPerformed(evt);
            }
        });

        dsTable.setModel(model);
        dsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(dsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(addNew)
                .addGap(32, 32, 32))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(addNew)
                    .addComponent(title))
                .addContainerGap(534, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(100, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(46, 46, 46)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewActionPerformed
        Sales_AddDailySales add_ds = new Sales_AddDailySales(ChangePanel);

        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(add_ds, BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_addNewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNew;
    private javax.swing.JTable dsTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
