/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.SALES;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author xiaochingloh
 */
public class PR {
    //Fixed selection for PR status
    public enum PR_Status{
        PENDING("PENDING"),
        APPROVED("APPROVED");
        
        private final String displayName;
        
        PR_Status(String displayName){
            this.displayName = displayName;
        }
        
        @Override
        public String toString(){
            return displayName;
        }
        
        public static PR_Status fromString(String text){
            for(PR_Status status : PR_Status.values()){
                if(status.displayName.equalsIgnoreCase(text)){
                    return status;
                }
            }
            
            return null;
        }
    }
    private String PRID;
    private LocalDate date;
    private List<PRItem> items;
    private String raisedBy;
    private PR_Status status;
    
    //Empty Constructor
    public PR(){}
    
    //Constructor
    public PR(String PRID, LocalDate date, List<PRItem> items, String raisedBy, PR_Status status){
        this.PRID = PRID;
        this.date = date;
        this.items = items;
        this.raisedBy = raisedBy;
        this.status = status;
    }

    public String getPRID() {
        return PRID;
    }

    public void setPRID(String PRID) {
        this.PRID = PRID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<PRItem> getItems() {
        return items;
    }

    public void setItems(List<PRItem> items) {
        this.items = items;
    }

    public String getRaisedBy() {
        return raisedBy;
    }

    public void setRaisedBy(String raisedBy) {
        this.raisedBy = raisedBy;
    }

    public PR_Status getStatus() {
        return status;
    }

    public void setStatus(PR_Status status) {
        this.status = status;
    }
    
    public static void savePRToFile(PR pr){
        try{
            //Get Path
            String classPath = PR.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            
            File dbDir = new File(baseDir.getParentFile(), "database");
            if (!dbDir.exists()) {
                dbDir.mkdirs(); 
            }
            
            File file = new File(dbDir, "PR.txt");
            boolean fileExists = file.exists();
            
            //Write File (Append)
            try (
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw)
            ) {
                if (!fileExists) {
                    bw.write("PRID;ItemCode;ItemName;Quantity;SupplierID;RequiredDeliveryDate;RaisedBy;Status");
                    bw.newLine();
                }

                for (PRItem item : pr.getItems()) {
                    String itemName = Items.getItemByCode(item.getItemCode()).getItemName(); // Get from Items class

                    bw.write(pr.getPRID() + ";" +
                             item.getItemCode() + ";" +
                             itemName + ";" +
                             item.getQuantity() + ";" +
                             item.getSupplierID() + ";" +
                             pr.getDate() + ";" +
                             pr.getRaisedBy() + ";" +
                             pr.getStatus());
                    bw.newLine();
                }

                bw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}
