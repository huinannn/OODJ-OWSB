/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.OWSB.SALES;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author xiaochingloh
 */
public class Sales_PR extends javax.swing.JPanel {
    private javax.swing.JPanel ChangePanel;
    private DefaultTableModel model = new DefaultTableModel();
    private String[] columnName = {"PRID", "ITEM", "QUANTITY", "SUPPLIER", "STATUS", "RAISED BY", "REQUIRED DELIVERY DATE", "ACTION"};
    /**
     * Creates new form Sales_PR
     */
    public Sales_PR(javax.swing.JPanel ChangePanel) {
        model.setColumnIdentifiers(columnName);
        initComponents();
        prTable = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                
                int alertCol = 4; 

                if (!isRowSelected(row)) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(240, 240, 240)); 
                    }
                    c.setForeground(Color.BLACK);
                }

                
                //If Approved, color green; Disclaimed, color gray; Pending, color yellow;
                if (column == alertCol) {
                    Object value = getValueAt(row, column);
                    if (value != null && value.toString().equalsIgnoreCase("pending")) {
                        c.setBackground(Color.YELLOW);
                        c.setForeground(Color.BLACK);
                    } else if (value != null && value.toString().equalsIgnoreCase("approved")) {
                        c.setBackground(Color.GREEN);
                        c.setForeground(Color.BLACK);
                    }else if (value != null && value.toString().equalsIgnoreCase("disclaimed")){
                        c.setBackground(new Color(220, 220, 220)); 
                        c.setForeground(Color.BLACK);
                    } else {
                        c.setBackground(new Color(240, 240, 240)); 
                        c.setForeground(Color.BLACK);
                    }
                }
                
                return c;
            }
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        prTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(prTable);
        this.ChangePanel = ChangePanel;
        for (int i = 0; i < prTable.getColumnCount() - 1; i++) {
            prTable.getColumnModel().getColumn(i).setPreferredWidth(110);
            prTable.setRowHeight(30);
        }
        prTable.getColumnModel().getColumn(prTable.getColumnCount() - 7).setPreferredWidth(200);
        prTable.getColumnModel().getColumn(prTable.getColumnCount() - 5).setPreferredWidth(200);
        prTable.getColumnModel().getColumn(prTable.getColumnCount() - 1).setPreferredWidth(120);
        prTable.setFont(new java.awt.Font("Georgia", java.awt.Font.PLAIN, 12));
        List<PR> data = PR.viewPRsInFile();
        //Map PR.txt itemCode(FK) with inventory.txt itemCode(PK)
        Map<String, String[]> itemCodeToPRMap = new HashMap<>();
        List<Items> items = Sales_AddItem.viewItemsInFile();
        for (Items item : items) {
            String prItemCode = item.getItemCode();
            String[] itemDetail = {item.getItemCode(), item.getItemName()};
            itemCodeToPRMap.put(prItemCode, itemDetail);
        }
        //Map supplier.txt itemCode(FK) with inventory.txt itemCode(PK)
        Map<String, List<String>> itemCodeToSuppliersMap = new HashMap<>();
        List<Suppliers> suppliers = Sales_Supplier.viewSuppliersInFile();
        for (Suppliers supplier : suppliers){
            String suppliedItemCode = supplier.getItemSupplied().split(":")[0].trim();
            String supplierInfo = supplier.getSupplierID() + "-" + supplier.getSupplierName();
            itemCodeToSuppliersMap.computeIfAbsent(suppliedItemCode, k -> new ArrayList<>()).add(supplierInfo);
        }
        for(PR pr : data){
            String[] itemDetail = itemCodeToPRMap.getOrDefault(pr.getItemCode(), new String[]{});
            String item = (itemDetail.length >= 2 ? itemDetail[0] + " - " + itemDetail[1] : "");

            List<String> suppliersList = itemCodeToSuppliersMap.getOrDefault(pr.getItemCode(), new ArrayList<>());
            String supplierInfo = suppliersList.isEmpty() ? "N/A" : "<html>" + String.join("<br>", suppliersList) + "</html>";
            model.addRow(new Object[] {
                pr.getPRID(),
                item,
                pr.getQuantity(),
                supplierInfo,
                pr.getStatus(),
                pr.getRaisedBy(),
                pr.getDate(),
                pr.getStatus().toString().equalsIgnoreCase("PENDING") ? "Edit/Delete" : ""
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
                String status = table.getValueAt(row, 4).toString();
                Component comp;

                if ("PENDING".equalsIgnoreCase(status)) {
                    JButton button = new JButton(value != null ? value.toString() : "Edit/Delete");
                    comp = button;
                } else {
                    comp = new JLabel(""); 
                }

                if (!isSelected) {
                    if (row % 2 == 0) {
                        comp.setBackground(Color.WHITE);
                    } else {
                        comp.setBackground(new Color(240, 240, 240));
                    }
                } else {
                    comp.setBackground(table.getSelectionBackground());
                }

                if (comp instanceof JComponent) {
                    ((JComponent) comp).setOpaque(true);
                }

                return comp;
            }
        });

        
        prTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = prTable.rowAtPoint(e.getPoint());
                int col = prTable.columnAtPoint(e.getPoint());
                if (col == prTable.getColumnCount() - 1) {
                    String status = prTable.getValueAt(row, 4).toString();
                    if ("pending".equalsIgnoreCase(status)) {
                        // Show popup with Edit/Delete
                        JPopupMenu popup = new JPopupMenu();
                        JMenuItem edit = new JMenuItem("Edit");
                        JMenuItem delete = new JMenuItem("Delete");
                        
                        edit.addActionListener(ae -> {
                            int selectedRow = prTable.getSelectedRow();
                            if (selectedRow >= 0){
                                String prID = prTable.getValueAt(selectedRow, 0).toString();
                                System.out.println("Sales PR: " + prID);
                                Sales_EditPR edit_pr = new Sales_EditPR(prID, ChangePanel);
                                ChangePanel.removeAll();
                                ChangePanel.setLayout(new BorderLayout());
                                ChangePanel.add(edit_pr, BorderLayout.CENTER);
                                ChangePanel.revalidate();
                                ChangePanel.repaint();
                            } else {
                                JOptionPane.showMessageDialog(null, "Please select a purchase requisition to edit!");
                            }

                        });

                        delete.addActionListener(ae ->{
                            int selectedRow = prTable.getSelectedRow();
                            if (selectedRow >= 0) {
                                String PRID = prTable.getValueAt(selectedRow, 0).toString();
                                String item = prTable.getValueAt(selectedRow, 1).toString();

                                int confirm = JOptionPane.showConfirmDialog(
                                    null,
                                    "Are you sure you want to delete PR that contains item: " + item + "?",
                                    "Confirm Deletition",
                                    JOptionPane.YES_NO_OPTION
                                );

                                if(confirm == JOptionPane.YES_OPTION){
                                    deletePRsInFile(PRID);
                                    ((DefaultTableModel) prTable.getModel()).removeRow(selectedRow);
                                    JOptionPane.showMessageDialog(null, "PRID: " + PRID + ", Item Name: " + item + "\nDeleted!");
                                }
                            }
                        });

                        popup.add(edit);
                        popup.add(delete);
                        popup.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });
    }
    
    private void adjustRowHeights() {
        int supplierColumn = 3; 

        for (int row = 0; row < prTable.getRowCount(); row++) {
            TableCellRenderer renderer = prTable.getCellRenderer(row, supplierColumn);
            Component comp = prTable.prepareRenderer(renderer, row, supplierColumn);
            int height = comp.getPreferredSize().height + 10;

            if (height > prTable.getRowHeight()) {
                prTable.setRowHeight(row, height);
            }
        }
    }
    
    public static void deletePRsInFile(String PRID){
        try{
            String classPath = PR.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "PR.txt");
            if(!file.exists()){
                 JOptionPane.showMessageDialog(null, "PR.txt file does not exist.");
                return;
            }
            
            List<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                boolean isFirstLine = true;
                while ((line = br.readLine()) != null) {
                    if (isFirstLine) {
                        lines.add(line);
                        isFirstLine = false;
                        continue;
                    }
                    
                    //Match PRID
                    String[] parts = line.split(";");
                    if (!parts[0].equals(PRID)) {
                        lines.add(line);
                    }
                }
            }
            
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (String l : lines) {
                    bw.write(l);
                    bw.newLine();
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(483, Short.MAX_VALUE)
                .addComponent(addNew)
                .addGap(32, 32, 32))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(title)
                            .addGap(26, 245, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(addNew)
                .addGap(488, 488, 488))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(title)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(46, 46, 46)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewActionPerformed
        Sales_AddPR pr = new Sales_AddPR(ChangePanel);

        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(pr, BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_addNewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNew;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable prTable;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
