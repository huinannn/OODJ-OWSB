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
public final class Sales_AddItem extends javax.swing.JPanel {
    private javax.swing.JPanel ChangePanel;
    /**
     * Creates new form Sales_AddItem
     */
    public Sales_AddItem(javax.swing.JPanel ChangePanel) {
        initComponents();
        this.ChangePanel = ChangePanel;
        
        //Generate new itemID
        String newID = generateNextItemID();
        itemID.setText(newID);
        itemID.setEditable(false);
        itemID.setBorder(null);
        
        //Generate list of categories
        for(Items.Category cat: Items.Category.values()){
            category.addItem(cat.toString());
        }
    }
    
    public String generateNextItemID(){
        List<Items>itemList = viewItemsInFile();
        int maxID = 0;
        
        for(Items item : itemList){
             String id = item.getItemCode();
            if(id.startsWith("ITM")){
                try{
                    int numericPart = Integer.parseInt(id.substring(3));
                    if(numericPart > maxID){
                        maxID = numericPart;
                    }
                } catch(NumberFormatException e){
                    
                }
            }
        }
        
        int nextID = maxID +1;
        return String.format("ITM%03d", nextID);
    }
    
    public static void saveItemToFile(Items item) {
        try {
            //Get Path
            String classPath = Items.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile(); 

            File dbDir = new File(baseDir.getParentFile(), "database");
            if (!dbDir.exists()) {
                dbDir.mkdirs(); 
            }

            File file = new File(dbDir, "Inventory.txt");
            boolean fileExists = file.exists();
            
            //Write File (Append)
            try (
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw)
            ) {
                if (!fileExists) {
                    bw.write("ItemCode;ItemName;Category;StockCurrentQuantities;ReorderLevel;Description;ReorderAlertStatus");
                    bw.newLine();
                }
                
                

                bw.write(item.getItemCode() + ";" +
                         item.getItemName() + ";" +
                         item.getCategory() + ";" +
                         item.getStockCurrentQuantities() + ";" +
                         item.getReorderLevel() + ";" +
                         item.getDescription() + ";" +
                         item.getReorderStatus());
                bw.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static List<Items> viewItemsInFile() {
        List<Items> itemList = new ArrayList<>();
        
        try {
            String classPath = Items.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "Inventory.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "Inventory.txt file does not exist.");
                return itemList;
            }

            try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)
            ) {
               String line;
               boolean isFirstLine = true;
               
                while((line = br.readLine()) != null){
                   if (isFirstLine){
                       isFirstLine = false;
                       continue;
                   }
                   
                    if (!line.trim().isEmpty()) {
                        String[] row = line.split(";");
                        if (row.length >= 7) {
                            Items.Category category = Items.Category.fromString(row[2]);
                            Items.ReorderAlertStatus status = Items.ReorderAlertStatus.fromString(row[6]);
                            int quantity = Integer.parseInt(row[3]);
                            int reorder_level = Integer.parseInt(row[4]); 
                            Items item = new Items(
                                row[0], 
                                row[1], 
                                category, 
                                quantity,
                                reorder_level,
                                row[5], 
                                status
                            );
                            itemList.add(item);
                        }
                    }
               }
            }
            
            

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return itemList;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stockLevel_label1 = new javax.swing.JLabel();
        stockLevel1 = new javax.swing.JTextField();
        itemID_label = new javax.swing.JLabel();
        itemName_label = new javax.swing.JLabel();
        category_label = new javax.swing.JLabel();
        description_label = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        add = new javax.swing.JButton();
        itemID = new javax.swing.JTextField();
        itemName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        title = new javax.swing.JLabel();
        category = new javax.swing.JComboBox<>();

        stockLevel_label1.setText("STOCK LEVEL");

        stockLevel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockLevel1ActionPerformed(evt);
            }
        });

        setBackground(new java.awt.Color(255, 255, 255));

        itemID_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        itemID_label.setText("ITEM ID");

        itemName_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        itemName_label.setText("ITEM NAME*");

        category_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        category_label.setText("CATEGORY*");

        description_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        description_label.setText("DESCRIPTION");

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

        itemID.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        itemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIDActionPerformed(evt);
            }
        });

        itemName.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        itemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNameActionPerformed(evt);
            }
        });

        description.setColumns(20);
        description.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        description.setRows(5);
        jScrollPane1.setViewportView(description);

        title.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        title.setText("NEW ITEM");

        category.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please Select an Item Category!"}));
        category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(title)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(category_label)
                    .addComponent(description_label)
                    .addComponent(itemID_label)
                    .addComponent(itemName_label))
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemID)
                    .addComponent(itemName)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(category, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(105, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(386, Short.MAX_VALUE)
                .addComponent(back)
                .addGap(36, 36, 36)
                .addComponent(add)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(title)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemID_label)
                    .addComponent(itemID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemName_label)
                    .addComponent(itemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(category_label)
                    .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(description_label)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(add))
                .addContainerGap(227, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        Sales_Item item = new Sales_Item(ChangePanel);
        
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(item, BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_backActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        //Empty Fields, not allowed to submit
        if(itemName.getText().trim().isEmpty()||  category.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(this, "Please fill in all required fields!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        
        
        String item_ID = itemID.getText();
        String item_name = itemName.getText();
        int stock_level = 0;
        int reorder_level = 10;
        String category_selected = category.getSelectedItem().toString();
        Items.Category selectedCategory = Items.Category.fromString(category_selected);
        String description_given = description.getText();
        String reorder_status = "LOW STOCK";
        Items.ReorderAlertStatus reorderStatus = Items.ReorderAlertStatus.fromString(reorder_status);
        
        Items newItem = new Items(item_ID, item_name, selectedCategory, stock_level, reorder_level, description_given, reorderStatus);
        
        saveItemToFile(newItem);
        
        //Back to Items table
        Sales_Item item = new Sales_Item(ChangePanel);
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(item, BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
        
    }//GEN-LAST:event_addActionPerformed

    private void itemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNameActionPerformed
        
    }//GEN-LAST:event_itemNameActionPerformed

    private void itemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIDActionPerformed
        
    }//GEN-LAST:event_itemIDActionPerformed

    private void stockLevel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockLevel1ActionPerformed
        
    }//GEN-LAST:event_stockLevel1ActionPerformed

    private void categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton back;
    private javax.swing.JComboBox<String> category;
    private javax.swing.JLabel category_label;
    private javax.swing.JTextArea description;
    private javax.swing.JLabel description_label;
    private javax.swing.JTextField itemID;
    private javax.swing.JLabel itemID_label;
    private javax.swing.JTextField itemName;
    private javax.swing.JLabel itemName_label;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField stockLevel1;
    private javax.swing.JLabel stockLevel_label1;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
