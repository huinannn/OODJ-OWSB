/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package com.mycompany.OWSB.FINANCE;
import com.mycompany.OWSB.PURCHASE.PO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Thong Hui Nan
 */

public class Finance_EditPO extends javax.swing.JPanel {
    private final javax.swing.JPanel ChangePanel;
    private final String poId;
    private PO currentPO;
    
    public Finance_EditPO(String poId, javax.swing.JPanel ChangePanel) {
        this.poId = poId;
        this.ChangePanel = ChangePanel;
        initComponents();
        loadPOData();
        loadRelevantSuppliersForItem(); 
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
        
    private void loadPOData() {
        List<PO> poList = loadPOListFromFile();
        Map<String, String> empMap = loadAllUsers();

        for (PO po : poList) {
            if (po.getPOID().equals(poId)) {
                currentPO = po;
                POID.setText(po.getPOID());
                ItemID.setText(po.getItemID());
                ItemName.setText(po.getItemName());
                Quantity_TextField.setText(String.valueOf(po.getQuantity()));
                TotalPrice.setText(String.format("RM " +"%.2f", po.getTotalPrice()));
                Supplier_Dropdown.setSelectedItem(po.getSupplierID());

                String empID = po.getRaisedBy();
                String raisedBy = empID;
                if (empMap.containsKey(empID)) {
                    String username = empMap.get(empID);
                    raisedBy = empID + " - " + username;
                }
                RaisedBy.setText(raisedBy);

                DeliveryDate.setText(po.getDeliveryDate());
                Status_Dropdown.setSelectedItem(po.getStatus());
                break;
            }
        }

        if (currentPO == null) {
            JOptionPane.showMessageDialog(this, "PO not found: " + poId);
        }
    }

    private List<PO> loadPOListFromFile() {
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
            JOptionPane.showMessageDialog(this, "Error loading PO list: " + e.getMessage());
        }
        return poList;
    }
    
    private void loadRelevantSuppliersForItem() {
        Supplier_Dropdown.removeAllItems();

        String currentSupplierID = currentPO.getSupplierID().trim();
        String itemID = currentPO.getItemID();

        List<String> allSuppliers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("database/Supplier.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 7) {
                    String supplierID = parts[0].trim();
                    String supplierName = parts[1].trim();
                    String itemsSupplied = parts[6].trim();

                    if (itemsSupplied.startsWith(itemID)) {
                        String displayText = supplierID + " - " + supplierName;
                        allSuppliers.add(displayText);
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading suppliers: " + e.getMessage());
            return;
        }

        allSuppliers.sort((a, b) -> {
            String idA = a.split(" - ")[0].replaceAll("[^0-9]", "");
            String idB = b.split(" - ")[0].replaceAll("[^0-9]", "");
            int numA = idA.isEmpty() ? 0 : Integer.parseInt(idA);
            int numB = idB.isEmpty() ? 0 : Integer.parseInt(idB);
            return Integer.compare(numA, numB);
        });

        for (String s : allSuppliers) {
            Supplier_Dropdown.addItem(s);
        }

        for (int i = 0; i < Supplier_Dropdown.getItemCount(); i++) {
            String item = Supplier_Dropdown.getItemAt(i);
            if (item.startsWith(currentSupplierID)) {
                Supplier_Dropdown.setSelectedIndex(i);
                break;
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ItemName_Label = new javax.swing.JLabel();
        ItemID_Label = new javax.swing.JLabel();
        POID_Label = new javax.swing.JLabel();
        SaveButton = new javax.swing.JButton();
        EditPO_Label = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();
        Supplier_Dropdown = new javax.swing.JComboBox<>();
        Quantity_TextField = new javax.swing.JTextField();
        Status_Label = new javax.swing.JLabel();
        Quantity_Label = new javax.swing.JLabel();
        TotalPrice_Label = new javax.swing.JLabel();
        Supplier_Label = new javax.swing.JLabel();
        RaisedBy_Label = new javax.swing.JLabel();
        DeliveryDate_Label = new javax.swing.JLabel();
        Status_Dropdown = new javax.swing.JComboBox<>();
        POID = new javax.swing.JLabel();
        ItemID = new javax.swing.JLabel();
        ItemName = new javax.swing.JLabel();
        TotalPrice = new javax.swing.JLabel();
        RaisedBy = new javax.swing.JLabel();
        DeliveryDate = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        ItemName_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        ItemName_Label.setText("Item Name");

        ItemID_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        ItemID_Label.setText("Item ID");

        POID_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        POID_Label.setText("PO ID");

        SaveButton.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        EditPO_Label.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        EditPO_Label.setText("Edit Purchase Order");

        BackButton.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        Supplier_Dropdown.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Supplier_Dropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Supplier_DropdownActionPerformed(evt);
            }
        });

        Quantity_TextField.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N

        Status_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        Status_Label.setText("Status");

        Quantity_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        Quantity_Label.setText("Quantity");

        TotalPrice_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        TotalPrice_Label.setText("Total Price");

        Supplier_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        Supplier_Label.setText("Supplier");

        RaisedBy_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        RaisedBy_Label.setText("Raised By");

        DeliveryDate_Label.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        DeliveryDate_Label.setText("Delivery Date");

        Status_Dropdown.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Status_Dropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Approved", "Disclaimed" }));
        Status_Dropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Status_DropdownActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(BackButton, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                        .addGap(60, 60, 60)
                        .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                        .addGap(67, 67, 67))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Quantity_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TotalPrice_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Supplier_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(RaisedBy_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(DeliveryDate_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Status_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ItemName_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(POID_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(88, 88, 88))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ItemID_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(77, 77, 77)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Supplier_Dropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Status_Dropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Quantity_TextField))
                                .addGap(10, 10, 10))
                            .addComponent(ItemName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(RaisedBy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DeliveryDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(POID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ItemID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(9, 9, 9)))))
                .addGap(91, 91, 91))
            .addGroup(layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addComponent(EditPO_Label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(EditPO_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(POID_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(POID, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addGap(15, 15, 15)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ItemID_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ItemID, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addGap(15, 15, 15)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ItemName_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ItemName, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addGap(15, 15, 15)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Quantity_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addComponent(Quantity_TextField))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TotalPrice_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addGap(15, 15, 15)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Supplier_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addComponent(Supplier_Dropdown))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(RaisedBy_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(RaisedBy, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addGap(15, 15, 15)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DeliveryDate_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DeliveryDate, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addGap(15, 15, 15)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Status_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Status_Dropdown, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BackButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(46, 46, 46))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Status_DropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Status_DropdownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Status_DropdownActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        if (currentPO == null) return;

        try {
            int quantity = Integer.parseInt(Quantity_TextField.getText().trim());

            String selectedSupplier = Supplier_Dropdown.getSelectedItem().toString();
            String updatedSupplier = selectedSupplier.split(" - ")[0];

            String updatedStatusStr = Status_Dropdown.getSelectedItem().toString();
            PO.PO_Status updatedStatus = PO.PO_Status.fromString(updatedStatusStr);

            boolean noChange = updatedStatus.equals(currentPO.getStatus()) &&
                    (quantity == currentPO.getQuantity()) &&
                    updatedSupplier.equals(currentPO.getSupplierID());

            if (noChange) {
                JOptionPane.showMessageDialog(this, "Please modify at least one field before saving.");
                return;
            }

            double unitPrice = 0;
            String itemCode = currentPO.getItemID();
            try (BufferedReader br = new BufferedReader(new FileReader("database/Inventory.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length >= 5 && parts[0].equals(itemCode)) {
                        unitPrice = Double.parseDouble(parts[4]);
                        break;
                    }
                }
            }

            if (unitPrice == 0) {
                JOptionPane.showMessageDialog(this, "Unit price not found for item: " + itemCode);
                return;
            }

            double totalPrice = unitPrice * quantity;

            List<PO> poList = loadPOListFromFile();
            boolean updated = false;
            for (int i = 0; i < poList.size(); i++) {
                PO po = poList.get(i);
                if (po.getPOID().equals(currentPO.getPOID())) {
                    PO updatedPO = new PO(
                        po.getPOID(),
                        po.getItemID(),
                        po.getItemName(),
                        quantity,
                        totalPrice,
                        updatedSupplier,
                        po.getRaisedBy(),
                        po.getDeliveryDate(),
                        updatedStatus
                    );
                    poList.set(i, updatedPO);
                    currentPO = updatedPO;
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                JOptionPane.showMessageDialog(this, "PO not found to update.");
                return;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("PO_Lists.txt"))) {
                for (PO po : poList) {
                    String line = String.join(";",
                    po.getPOID(),
                    po.getItemID(),
                    po.getItemName(),
                    String.valueOf(po.getQuantity()),
                    String.format("%.2f", po.getTotalPrice()),
                    po.getSupplierID(),
                    po.getRaisedBy(),
                    po.getDeliveryDate(),
                    po.getStatus().toString()
                );
                    bw.write(line);
                    bw.newLine();
                }
            }

            TotalPrice.setText(String.format("%.2f", totalPrice));
            JOptionPane.showMessageDialog(this, "PO Update successfully!");

            ChangePanel.removeAll();
            ChangePanel.setLayout(new BorderLayout());
            ChangePanel.add(new Finance_ViewPO(ChangePanel), BorderLayout.CENTER);
            ChangePanel.revalidate();
            ChangePanel.repaint();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid quantity.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(new Finance_ViewPO(ChangePanel), BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_BackButtonActionPerformed

    private void Supplier_DropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Supplier_DropdownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Supplier_DropdownActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JLabel DeliveryDate;
    private javax.swing.JLabel DeliveryDate_Label;
    private javax.swing.JLabel EditPO_Label;
    private javax.swing.JLabel ItemID;
    private javax.swing.JLabel ItemID_Label;
    private javax.swing.JLabel ItemName;
    private javax.swing.JLabel ItemName_Label;
    private javax.swing.JLabel POID;
    private javax.swing.JLabel POID_Label;
    private javax.swing.JLabel Quantity_Label;
    private javax.swing.JTextField Quantity_TextField;
    private javax.swing.JLabel RaisedBy;
    private javax.swing.JLabel RaisedBy_Label;
    private javax.swing.JButton SaveButton;
    private javax.swing.JComboBox<String> Status_Dropdown;
    private javax.swing.JLabel Status_Label;
    private javax.swing.JComboBox<String> Supplier_Dropdown;
    private javax.swing.JLabel Supplier_Label;
    private javax.swing.JLabel TotalPrice;
    private javax.swing.JLabel TotalPrice_Label;
    // End of variables declaration//GEN-END:variables
}