/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package com.mycompany.OWSB.INVENTORY;


import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 *
 * @author Hui Nan
 */

public class IM_Dashboard extends javax.swing.JFrame {
    
    /**
     * Creates new form Finance_Dashboard
     */
    
    public IM_Dashboard() {
        setTitle("Inventory Manager Dashboard");
        initComponents();
        setLocationRelativeTo(null); 

        try {
            updateStockFromPO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStockFromPO() throws IOException {
        List<String[]> poList = readFile("PO_Lists.txt");
        List<String[]> stockList = readOrCreateFile("Stock.txt");
        List<String[]> inventoryList = readFile("../OODJ/database/Inventory.txt");

        Set<String> existingPOIDs = new HashSet<>();
        Set<String> existingStockIDs = new HashSet<>();

        for (int i = 1; i < stockList.size(); i++) {
            existingPOIDs.add(stockList.get(i)[1]);
            existingStockIDs.add(stockList.get(i)[0]);
        }

        Map<String, Integer> inventoryQuantities = new HashMap<>();
        for (int i = 1; i < inventoryList.size(); i++) {
            String[] parts = inventoryList.get(i);
            inventoryQuantities.put(parts[0], Integer.parseInt(parts[3]));
        }

        LocalDate today = LocalDate.now();
        DateTimeFormatter[] dateFormats = {
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        };

        
        List<String[]> newStockEntries = new ArrayList<>();

        for (String[] po : poList) {
            if (po.length < 9) continue;

            String poid = po[0];
            String itemid = po[1];
            String itemname = po[2];
            int newQty = Integer.parseInt(po[3]);
            double unitPrice = Double.parseDouble(po[4]);
            String supplier = po[5];
            String deliveryDateStr = po[7];
            String status = po[8];

            // Process only approved POs
            if (!status.equals("Approved")) continue;

            // Skip if already processed
            if (existingPOIDs.contains(poid)) continue;

            // Parse delivery date
            LocalDate deliveryDate = null;
            for (DateTimeFormatter format : dateFormats) {
                try {
                    deliveryDate = LocalDate.parse(deliveryDateStr, format);
                    break;
                } catch (Exception ignored) {}
            }
            if (deliveryDate == null || deliveryDate.isAfter(today)) continue;

            int prevQty = inventoryQuantities.getOrDefault(itemid, 0);
            int currQty = prevQty + newQty;
            double totalPrice = newQty * unitPrice;

            String newStockID = generateStockID(existingStockIDs);
            existingStockIDs.add(newStockID);

            String[] newEntry = {
                    newStockID,
                    poid,
                    itemid,
                    itemname,
                    String.valueOf(newQty),
                    String.format("%.2f", totalPrice),
                    supplier,
                    deliveryDate.format(dateFormats[0]),
                    String.valueOf(prevQty),
                    String.valueOf(currQty),
                    "Pending",
                    "-",
                    "Unpaid"
            };

            newStockEntries.add(newEntry);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Stock.txt", true))) {
            if (stockList.size() == 0) {
                bw.write("NewStockID;POID;ItemID;ItemName;NewStockQuantity;TotalPrice;Supplier;StockArrivalDate;ItemPreviousQuantity;ItemCurrentQuantity;StockApprovalStatus;StockApprovalDate;PaymentStatus\n");
            }
            for (String[] entry : newStockEntries) {
                bw.write(String.join(";", entry));
                bw.newLine();
            }
        }

        System.out.println("Stock update completed.");
    }
    private static List<String[]> readFile(String filename) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    data.add(line.split(";", -1));
                }
            }
        }
        return data;
    }

    private static List<String[]> readOrCreateFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
            return new ArrayList<>();
        } else {
            return readFile(filename);
        }
    }

    private static String generateStockID(Set<String> existingIDs) {
        int i = 1;
        while (true) {
            String id = String.format("NS%03d", i);
            if (!existingIDs.contains(id)) return id;
            i++;
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

        SideBar = new javax.swing.JPanel();
        ProfilePicture = new javax.swing.JLabel();
        FMRole = new javax.swing.JLabel();
        ViewProfile = new javax.swing.JButton();
        ViewInventory = new javax.swing.JButton();
        View_Approved_PO = new javax.swing.JButton();
        Arrived_Stock = new javax.swing.JButton();
        Stock_History = new javax.swing.JButton();
        LogOut = new javax.swing.JButton();
        IM_Panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SideBar.setBackground(new java.awt.Color(42, 160, 97));

        ProfilePicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/Admin.png"))); // NOI18N

        FMRole.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        FMRole.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FMRole.setText("Inventory Manager");

        ViewProfile.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        ViewProfile.setText("View Profile");
        ViewProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewProfileActionPerformed(evt);
            }
        });

        ViewInventory.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        ViewInventory.setText("View Inventory");
        ViewInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewInventoryActionPerformed(evt);
            }
        });

        View_Approved_PO.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        View_Approved_PO.setText("Approved Purchase Orders");
        View_Approved_PO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                View_Approved_POActionPerformed(evt);
            }
        });

        Arrived_Stock.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Arrived_Stock.setText("Arrived Stocks");
        Arrived_Stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Arrived_StockActionPerformed(evt);
            }
        });

        Stock_History.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Stock_History.setText("Stocks History");
        Stock_History.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Stock_HistoryActionPerformed(evt);
            }
        });

        LogOut.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        LogOut.setText("Log Out");
        LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SideBarLayout = new javax.swing.GroupLayout(SideBar);
        SideBar.setLayout(SideBarLayout);
        SideBarLayout.setHorizontalGroup(
            SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBarLayout.createSequentialGroup()
                .addGroup(SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SideBarLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SideBarLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(ViewProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                .addGap(47, 47, 47))
                            .addComponent(ViewInventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(View_Approved_PO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Arrived_Stock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Stock_History, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SideBarLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FMRole)
                        .addGap(32, 32, 32)))
                .addGap(32, 32, 32))
            .addGroup(SideBarLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(ProfilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SideBarLayout.setVerticalGroup(
            SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(ProfilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FMRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(59, 59, 59)
                .addComponent(ViewProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(ViewInventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(View_Approved_PO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(Arrived_Stock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(Stock_History, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(LogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        IM_Panel.setBackground(new java.awt.Color(255, 255, 255));
        IM_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(192, 192, 192), 5));

        javax.swing.GroupLayout IM_PanelLayout = new javax.swing.GroupLayout(IM_Panel);
        IM_Panel.setLayout(IM_PanelLayout);
        IM_PanelLayout.setHorizontalGroup(
            IM_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 627, Short.MAX_VALUE)
        );
        IM_PanelLayout.setVerticalGroup(
            IM_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IM_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SideBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(IM_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ViewProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewProfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewProfileActionPerformed

    private void ViewInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewInventoryActionPerformed
        IM_ViewInventory inventoryPanel = new IM_ViewInventory(); 

        IM_Panel.removeAll();
        IM_Panel.setLayout(new BorderLayout());
        IM_Panel.add(inventoryPanel, BorderLayout.CENTER);
        IM_Panel.revalidate();
        IM_Panel.repaint();
        
    }//GEN-LAST:event_ViewInventoryActionPerformed

    private void View_Approved_POActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_View_Approved_POActionPerformed
        // TODO add your handling code here:
//        IM_ViewApprovedPO inventoryPanel = new IM_ViewApprovedPO(); 
//
//        IM_Panel.removeAll();
//        IM_Panel.setLayout(new BorderLayout());
//        IM_Panel.add(inventoryPanel, BorderLayout.CENTER);
//        IM_Panel.revalidate();
//        IM_Panel.repaint();
    }//GEN-LAST:event_View_Approved_POActionPerformed

    private void Stock_HistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Stock_HistoryActionPerformed
        // TODO add your handling code here:
//        IM_ViewStockHistory inventoryPanel = new IM_ViewStockHistory(); 
//
//        IM_Panel.removeAll();
//        IM_Panel.setLayout(new BorderLayout());
//        IM_Panel.add(inventoryPanel, BorderLayout.CENTER);
//        IM_Panel.revalidate();
//        IM_Panel.repaint();
    }//GEN-LAST:event_Stock_HistoryActionPerformed

    private void LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutActionPerformed
//        new com.mycompany.OWSB.ADMIN.Login().setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_LogOutActionPerformed

    private void Arrived_StockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Arrived_StockActionPerformed
        // TODO add your handling code here:
//        IM_ManageStock inventoryPanel = new IM_ManageStock(); 
//
//        IM_Panel.removeAll();
//        IM_Panel.setLayout(new BorderLayout());
//        IM_Panel.add(inventoryPanel, BorderLayout.CENTER);
//        IM_Panel.revalidate();
//        IM_Panel.repaint();
    }//GEN-LAST:event_Arrived_StockActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IM_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IM_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IM_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IM_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IM_Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Arrived_Stock;
    private javax.swing.JLabel FMRole;
    private javax.swing.JPanel IM_Panel;
    private javax.swing.JButton LogOut;
    private javax.swing.JLabel ProfilePicture;
    private javax.swing.JPanel SideBar;
    private javax.swing.JButton Stock_History;
    private javax.swing.JButton ViewInventory;
    private javax.swing.JButton ViewProfile;
    private javax.swing.JButton View_Approved_PO;
    // End of variables declaration//GEN-END:variables
}