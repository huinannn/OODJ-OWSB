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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author xiaochingloh
 */
public class Sales_EditSupplier extends javax.swing.JPanel {
    private javax.swing.JPanel ChangePanel;
    private Suppliers supplier;
    /**
     * Creates new form Sales_EditSupplier2
     */
    public Sales_EditSupplier(String SupplierID, javax.swing.JPanel ChangePanel) {
        initComponents();
        
        this.ChangePanel = ChangePanel;
        //Generate Item Combo Box
        showItemSuppliedComboBox();
        
        //Load Supplier Details
        this.supplier = getSupplierByID(SupplierID);
        System.out.println("Edit Supplier: " + SupplierID);
        if(supplier != null){
            supplierID.setText(SupplierID);
            supplierID.setEditable(false);
            supplierID.setBorder(null);
            supplierName.setText(supplier.getSupplierName());
            contactPerson.setText(supplier.getContactPerson());
            phone.setText(supplier.getPhone());
            email.setText(supplier.getEmail());
            address.setText(supplier.getAddress());
            itemSupplied.setSelectedItem(supplier.getItemSupplied());
            price.setText(String.format("%.2f", supplier.getUnitPrice()));
            title.setText(supplier.getSupplierName());
            System.out.println(supplier.getSupplierName() + "," + supplier.getItemSupplied());
        }else {
            JOptionPane.showMessageDialog(null, "Supplier not found.");
        }
    }
    
    private void showItemSuppliedComboBox(){
        //Clear all items in combo box
        itemSupplied.removeAllItems();
        itemSupplied.addItem("Please Select Item Supplied!");
        
        List<Items> allItems = Sales_AddItem.viewItemsInFile();
        if (allItems != null && !allItems.isEmpty()) {
            for (Items item : allItems) {
                if (item != null) {
                    String itemCode = item.getItemCode();
                    String itemName = item.getItemName();
                    itemSupplied.addItem(itemCode + ": " + itemName);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No items found in inventory.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static Suppliers getSupplierByID(String SupplierID){
        try{
            String classPath = Suppliers.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "Supplier.txt");

            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "Supplier.txt file does not exist.");
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
                    if (parts.length >= 7 && parts[0].equals(SupplierID)) {
                        String supplierName = parts[1];
                        String contactPerson = parts[2];
                        String phone = parts[3];
                        String email = parts[4];
                        String address = parts[5];
                        String itemCode = parts[6];
                        double unitPrice = Double.parseDouble(parts[7]);
                        
                        return new Suppliers(SupplierID, supplierName, contactPerson, phone, email, address, itemCode, unitPrice);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading supplier: " + e.getMessage());
        }
        
        return null;
    }
    
    public static void editSuppliersInFile(String supplierID, Suppliers updatedSupplier){
        try {
            String classPath = Suppliers.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "Supplier.txt");

            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "Supplier.txt file does not exist.");
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
                    String formattedPrice = String.format("%.2f", updatedSupplier.getUnitPrice());
                    if (parts[0].equals(supplierID)) {
                        String updatedLine = updatedSupplier.getSupplierID() + ";" +
                                updatedSupplier.getSupplierName() + ";" +
                                updatedSupplier.getContactPerson() + ";" +
                                updatedSupplier.getPhone() + ";" +
                                updatedSupplier.getEmail() + ";" +
                                updatedSupplier.getAddress() + ";" +
                                updatedSupplier.getItemSupplied() + ";" +
                                formattedPrice;
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
            
            JOptionPane.showMessageDialog(null, "Supplier updated successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error editing supplier: " + e.getMessage());
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

        price_label = new javax.swing.JLabel();
        phone = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        phone_label = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        email_label = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        address_label = new javax.swing.JLabel();
        supplierID_label = new javax.swing.JLabel();
        address = new javax.swing.JTextField();
        supplierID = new javax.swing.JTextField();
        back = new javax.swing.JButton();
        supplier_name_label = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        supplierName = new javax.swing.JTextField();
        item_label = new javax.swing.JLabel();
        contact_person_label = new javax.swing.JLabel();
        itemSupplied = new javax.swing.JComboBox<>();
        contactPerson = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        price_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        price_label.setText("ITEM UNIT PRICE (RM)*");

        phone.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N

        price.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });

        phone_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        phone_label.setText("PHONE*");

        email.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N

        email_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        email_label.setText("EMAIL*");

        title.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        title.setText("SUPPLIER");

        address_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        address_label.setText("ADDRESS*");

        supplierID_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        supplierID_label.setText("SUPPLIER ID");

        address.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N

        supplierID.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N

        back.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        supplier_name_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        supplier_name_label.setText("SUPPLIER NAME*");

        add.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        add.setText("Save");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        supplierName.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N

        item_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        item_label.setText("ITEM SUPPLIED*");

        contact_person_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        contact_person_label.setText("CONTACT PERSON*");

        itemSupplied.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        itemSupplied.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please Select Item Supplied!" }));
        itemSupplied.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSuppliedActionPerformed(evt);
            }
        });

        contactPerson.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(title))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(supplierID_label)
                            .addComponent(supplier_name_label)
                            .addComponent(contact_person_label)
                            .addComponent(phone_label)
                            .addComponent(email_label)
                            .addComponent(address_label)
                            .addComponent(item_label)
                            .addComponent(price_label))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(supplierID, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(supplierName, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(contactPerson, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(phone, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(address, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(itemSupplied, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(price))))
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(back)
                .addGap(36, 36, 36)
                .addComponent(add)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(title)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplierID_label)
                    .addComponent(supplierID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplier_name_label)
                    .addComponent(supplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contact_person_label)
                    .addComponent(contactPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phone_label)
                    .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email_label)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(address_label)
                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(item_label)
                    .addComponent(itemSupplied, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(price_label)
                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(add))
                .addContainerGap(104, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed

    }//GEN-LAST:event_priceActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        Sales_Supplier s = new Sales_Supplier(ChangePanel);

        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(s, BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_backActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        //Empty Fields, not allowed to submit
        if(supplierName.getText().trim().isEmpty()|| contactPerson.getText().trim().isEmpty()|| phone.getText().trim().isEmpty()|| email.getText().trim().isEmpty()|| address.getText().trim().isEmpty()|| itemSupplied.getSelectedIndex() == 0|| price.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill in all required fields!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String priceInput = price.getText().trim();
        //Validate price
        if(!priceInput.matches("\\d+(\\.\\d+)?")){
            JOptionPane.showMessageDialog(this, "Please enter a valid price!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validate phone number
        String phoneInput = phone.getText().trim();
        if (!phoneInput.matches("^\\d{2,3}-?\\d{3,4}-?\\d{3,4}$")) {
            JOptionPane.showMessageDialog(this, "Invalid phone number! Only digits allowed (7–15 digits, optional '-').", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate email format
        String emailInput = email.getText().trim();
        if (!emailInput.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(this, "Invalid email address!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String supplier_ID = supplierID.getText();
        String supplier_name = supplierName.getText();
        String contact_person = contactPerson.getText();
        String phone_no = phone.getText();
        String email_given = email.getText();
        String address_given = address.getText();
        String item_supplied = itemSupplied.getSelectedItem().toString();
        double price_given = Double.parseDouble(priceInput);

        Suppliers updatedSupplier = new Suppliers(supplier_ID, supplier_name, contact_person, phone_no, email_given, address_given, item_supplied, price_given);

        editSuppliersInFile(supplier_ID, updatedSupplier);

        //Back to Suppliers table
        Sales_Supplier s = new Sales_Supplier(ChangePanel);
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(s, BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();

    }//GEN-LAST:event_addActionPerformed

    private void itemSuppliedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSuppliedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemSuppliedActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JTextField address;
    private javax.swing.JLabel address_label;
    private javax.swing.JButton back;
    private javax.swing.JTextField contactPerson;
    private javax.swing.JLabel contact_person_label;
    private javax.swing.JTextField email;
    private javax.swing.JLabel email_label;
    private javax.swing.JComboBox<String> itemSupplied;
    private javax.swing.JLabel item_label;
    private javax.swing.JTextField phone;
    private javax.swing.JLabel phone_label;
    private javax.swing.JTextField price;
    private javax.swing.JLabel price_label;
    private javax.swing.JTextField supplierID;
    private javax.swing.JLabel supplierID_label;
    private javax.swing.JTextField supplierName;
    private javax.swing.JLabel supplier_name_label;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
