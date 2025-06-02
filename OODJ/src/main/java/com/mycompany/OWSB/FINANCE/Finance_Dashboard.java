/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package com.mycompany.OWSB.FINANCE;
import com.mycompany.OWSB.ADMIN.Profile;
import java.awt.BorderLayout;

/**
 *
 * @author Thong Hui Nan
 */

public class Finance_Dashboard extends javax.swing.JFrame {
    
    public Finance_Dashboard() {
        setTitle("Finance Manager Dashboard");
        initComponents();
        setLocationRelativeTo(null); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ChangePanel = new javax.swing.JPanel();
        SideBar = new javax.swing.JPanel();
        ProfilePicture = new javax.swing.JLabel();
        SMRole = new javax.swing.JLabel();
        ViewProfile = new javax.swing.JButton();
        ViewPR = new javax.swing.JButton();
        ViewPO = new javax.swing.JButton();
        Inventory = new javax.swing.JButton();
        GenerateFinancial = new javax.swing.JButton();
        LogOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ChangePanel.setBackground(new java.awt.Color(255, 255, 255));
        ChangePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(192, 192, 192), 5));

        javax.swing.GroupLayout ChangePanelLayout = new javax.swing.GroupLayout(ChangePanel);
        ChangePanel.setLayout(ChangePanelLayout);
        ChangePanelLayout.setHorizontalGroup(
            ChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 595, Short.MAX_VALUE)
        );
        ChangePanelLayout.setVerticalGroup(
            ChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        SideBar.setBackground(new java.awt.Color(42, 160, 97));

        ProfilePicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/Finance_Manager.png"))); // NOI18N

        SMRole.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        SMRole.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SMRole.setText("Finance Manager");

        ViewProfile.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        ViewProfile.setText("View Profile");
        ViewProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewProfileActionPerformed(evt);
            }
        });

        ViewPR.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        ViewPR.setText("Purchase Requisitions");
        ViewPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPRActionPerformed(evt);
            }
        });

        ViewPO.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        ViewPO.setText("Purchase Orders");
        ViewPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPOActionPerformed(evt);
            }
        });

        Inventory.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Inventory.setText("Inventory");
        Inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InventoryActionPerformed(evt);
            }
        });

        GenerateFinancial.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        GenerateFinancial.setText("Financial Report");
        GenerateFinancial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateFinancialActionPerformed(evt);
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
                            .addComponent(ViewPR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ViewPO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Inventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(GenerateFinancial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SideBarLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SMRole, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(28, 28, 28)
                .addComponent(SMRole, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addGap(37, 37, 37)
                .addComponent(ViewProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(ViewPR, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(ViewPO, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(Inventory, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(GenerateFinancial, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(LogOut, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ChangePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SideBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ChangePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ViewProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewProfileActionPerformed
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(new Profile(ChangePanel), BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_ViewProfileActionPerformed

    private void ViewPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPRActionPerformed
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(new Finance_ViewPR(ChangePanel), BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_ViewPRActionPerformed

    private void ViewPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPOActionPerformed
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(new Finance_ViewPO(ChangePanel), BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_ViewPOActionPerformed

    private void InventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InventoryActionPerformed
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(new Finance_VerifyInventory(ChangePanel), BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
        
    }//GEN-LAST:event_InventoryActionPerformed

    private void GenerateFinancialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateFinancialActionPerformed
        ChangePanel.removeAll();
        ChangePanel.setLayout(new BorderLayout());
        ChangePanel.add(new Finance_FinancialReport(ChangePanel), BorderLayout.CENTER);
        ChangePanel.revalidate();
        ChangePanel.repaint();
    }//GEN-LAST:event_GenerateFinancialActionPerformed

    private void LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutActionPerformed
        new com.mycompany.OWSB.ADMIN.Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LogOutActionPerformed

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
            java.util.logging.Logger.getLogger(Finance_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Finance_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Finance_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Finance_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Finance_Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChangePanel;
    private javax.swing.JButton GenerateFinancial;
    private javax.swing.JButton Inventory;
    private javax.swing.JButton LogOut;
    private javax.swing.JLabel ProfilePicture;
    private javax.swing.JLabel SMRole;
    private javax.swing.JPanel SideBar;
    private javax.swing.JButton ViewPO;
    private javax.swing.JButton ViewPR;
    private javax.swing.JButton ViewProfile;
    // End of variables declaration//GEN-END:variables
}