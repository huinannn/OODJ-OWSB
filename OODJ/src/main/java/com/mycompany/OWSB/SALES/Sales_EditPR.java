/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.OWSB.SALES;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author xiaochingloh
 */
public class Sales_EditPR extends javax.swing.JPanel {
    private javax.swing.JPanel ChangePanel;
    private PR pr;
    /**
     * Creates new form Sales_EditPR
     */
    public Sales_EditPR(String prID, javax.swing.JPanel ChangePanel) {
        initComponents();
        this.ChangePanel = ChangePanel;
        
        //Generate list of items
        showItemComboBox();
        
        //Load PR details
        this.pr = getPRByPRID(prID);
        System.out.println("Edit PR: " + prID);
        if(pr != null){
            PRID.setText(prID);
            PRID.setEditable(false);
            PRID.setBorder(null);
            item.setSelectedItem(pr.getItemCode());
            quantity.setText(String.valueOf(pr.getQuantity()));
            date.setText(String.valueOf(pr.getDate()));
            raisedBy.setText(pr.getRaisedBy());
            title.setText(pr.getPRID());
        } else {
            JOptionPane.showMessageDialog(null, "Purchase Requisition not found.");
        }
        
    }
    
    private void showItemComboBox(){
        //Clear all items in combo box
        item.removeAllItems();
        item.addItem("Please Select Item Supplied!");
        
        List<Items> allItems = Sales_AddItem.viewItemsInFile();
        if (allItems != null && !allItems.isEmpty()) {
            for (Items i : allItems) {
                if (i != null) {
                    String itemCode = i.getItemCode();
                    String itemName = i.getItemName();
                    item.addItem(itemCode + ": " + itemName);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No items found in inventory.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static PR getPRByPRID(String PRID){
        try{
            String classPath = PR.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParent(), "database");
            File file = new File(dbDir, "PR.txt");
            
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "PR.txt file does not exist.");
                return null;
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
                    if (parts.length >= 6 && parts[0].trim().equalsIgnoreCase(PRID.trim())) {
                        String itemCode = parts[1];
                        int quantity = Integer.parseInt(parts[2]);
                        PR.PR_Status status = PR.PR_Status.fromString(parts[3]);
                        String raisedBy = parts[4];
                        LocalDate date = LocalDate.parse(parts[5]);
                        return new PR(PRID, itemCode, quantity, status, raisedBy, date);
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading item: " + e.getMessage());
        }

        return null;
    }
    
    public static void editPRsInFile(String PRID, PR updatedPR){
        try{
            String classPath = PR.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "PR.txt");
            
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "PR.txt file does not exist.");
                return;
            }
            
            List<String> lines = new ArrayList<>();
            boolean isFirstLine = true;

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while((line = br.readLine())!= null){
                    if (isFirstLine) {
                        lines.add(line); 
                        isFirstLine = false;
                        continue;
                    }
                    
                    String[] parts = line.split(";");
                    if (parts[0].equals(PRID)) {
                        
                        String updatedLine = updatedPR.getPRID() + ";" +
                                updatedPR.getItemCode() + ";" +
                                updatedPR.getQuantity() + ";" +
                                updatedPR.getStatus() + ";" +
                                updatedPR.getRaisedBy() + ";" +
                                updatedPR.getDate();
                        lines.add(updatedLine);
                    } else {
                        lines.add(line);
                    }
                }
            }
            
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
                for (String l : lines){
                    bw.write(l);
                    bw.newLine();
                }
            }
            
            JOptionPane.showMessageDialog(null, "Purchase Requisition updated successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error editing item: " + e.getMessage());
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
        date = new javax.swing.JTextField();
        PRID_label = new javax.swing.JLabel();
        item = new javax.swing.JComboBox<>();
        PRID = new javax.swing.JTextField();
        item_label = new javax.swing.JLabel();
        raisedBy = new javax.swing.JTextField();
        quantity_label = new javax.swing.JLabel();
        raisedBy_label = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        add = new javax.swing.JButton();
        quantity = new javax.swing.JTextField();
        date_label = new javax.swing.JLabel();

        title.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        title.setText("PURCHASE REQUISITION");

        date.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });

        PRID_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        PRID_label.setText("PR ID");

        item.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please Select an Item!"}));

        PRID.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        PRID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PRIDActionPerformed(evt);
            }
        });

        item_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        item_label.setText("ITEM*");

        raisedBy.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        raisedBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                raisedByActionPerformed(evt);
            }
        });

        quantity_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        quantity_label.setText("QUANTITY*");

        raisedBy_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        raisedBy_label.setText("RAISED BY*");

        back.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        add.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        add.setText("Save");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        quantity.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });

        date_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        date_label.setText("REQUIRED DELIVERY DATE*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(title)
                .addGap(0, 248, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(91, 386, Short.MAX_VALUE)
                .addComponent(back)
                .addGap(36, 36, 36)
                .addComponent(add)
                .addGap(31, 31, 31))
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quantity_label)
                    .addComponent(raisedBy_label)
                    .addComponent(PRID_label)
                    .addComponent(item_label)
                    .addComponent(date_label))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(date, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quantity, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(raisedBy, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PRID)
                    .addComponent(item, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(title)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(PRID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PRID_label))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(item_label)
                    .addComponent(item, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(quantity_label)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(date_label)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(raisedBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(raisedBy_label))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(add))
                .addContainerGap(204, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateActionPerformed

    private void PRIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PRIDActionPerformed

    }//GEN-LAST:event_PRIDActionPerformed

    private void raisedByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_raisedByActionPerformed

    }//GEN-LAST:event_raisedByActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        Sales_PR pr = new Sales_PR(ChangePanel);

        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(pr, BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_backActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        //Empty Fields, not allowed to submit
        if(item.getSelectedIndex() == 0|| quantity.getText().trim().isEmpty()|| date.getText().trim().isEmpty()|| raisedBy.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill in all required fields!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Validate Quantity
        String quantityInput = quantity.getText().trim();
        if(!quantityInput.matches("^\\d{1,4}$")){
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity! (Max 4 digits)", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate Date
        String dateInput = date.getText().trim();
        if (!dateInput.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Please enter date in format yyyy-MM-dd (e.g. 2025-06-20)", "Date Format Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            Date inputDate = sdf.parse(dateInput);
            Date today = sdf.parse(sdf.format(new Date()));

            if (inputDate.before(today)) {
                JOptionPane.showMessageDialog(this, "Date must be today or later!", "Date Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid date in yyyy-MM-dd format!", "Date Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String prID = PRID.getText();
        String itemDetail = item.getSelectedItem().toString();
        String itemCode = itemDetail.split(":")[0].trim();
        int quantity_given = Integer.parseInt(quantityInput);
        PR.PR_Status status = PR.PR_Status.PENDING;
        String raisedBy_given = raisedBy.getText();
        LocalDate date_given = LocalDate.parse(dateInput);

        PR newPR = new PR(prID, itemCode, quantity_given, status, raisedBy_given, date_given);

//        savePRToFile(newPR);

        //Back to PR table
        Sales_PR pr = new Sales_PR(ChangePanel);

        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(pr, BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_addActionPerformed

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField PRID;
    private javax.swing.JLabel PRID_label;
    private javax.swing.JButton add;
    private javax.swing.JButton back;
    private javax.swing.JTextField date;
    private javax.swing.JLabel date_label;
    private javax.swing.JComboBox<String> item;
    private javax.swing.JLabel item_label;
    private javax.swing.JTextField quantity;
    private javax.swing.JLabel quantity_label;
    private javax.swing.JTextField raisedBy;
    private javax.swing.JLabel raisedBy_label;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
