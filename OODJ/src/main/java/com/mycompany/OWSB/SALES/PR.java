/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.SALES;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author xiaochingloh
 */
public class PR {
    //Fixed selection for status
    public enum PR_Status{
        PENDING("PENDING"),
        APPROVED("APPROVED"),
        DISCLAIMED("DISCLAIMED");
        
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
    private String itemCode;
    private int quantity;
    private PR_Status status;
    private String raisedBy;
    private LocalDate date;
    
    //Empty Constructor
    public PR(){}
    
    //Sales Constructor
    public PR(String PRID, String itemCode, int quantity, PR_Status status, String raisedBy, LocalDate date){
        this.PRID = PRID;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.status = status;
        this.raisedBy = raisedBy;
        this.date = date;
    }

    public String getPRID() {
        return PRID;
    }

    public void setPRID(String PRID) {
        this.PRID = PRID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PR_Status getStatus() {
        return status;
    }

    public void setStatus(PR_Status status) {
        this.status = status;
    }

    public String getRaisedBy() {
        return raisedBy;
    }

    public void setRaisedBy(String RaisedBy) {
        this.raisedBy = RaisedBy;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public static List<PR> viewPRsInFile(){
        List<PR> prList = new ArrayList<>();
        
        try{
            String classPath = PR.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "PR.txt");
            if (!file.exists()){
                JOptionPane.showMessageDialog(null, "PR.txt file does not exist.");
                return prList;
            }
            
            try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
            ) {
                String line;
                boolean isFirstLine = true;
                
                while((line = br.readLine()) != null){
                    if (isFirstLine){
                        isFirstLine = false;
                        continue;
                    }
                    
                    if (!line.trim().isEmpty()){
                        String[] row = line.split(";");
                        if (row.length >= 6) {
                            int quantity = Integer.parseInt(row[2]);
                            PR.PR_Status status = PR.PR_Status.fromString(row[3]);
                            LocalDate date = LocalDate.parse(row[5]);
                            PR pr = new PR(
                                row[0], 
                                row[1], 
                                quantity, 
                                status,
                                row[4], 
                                date
                            );
                            prList.add(pr);
                        } else {
                            System.err.println("Invalid row: " + Arrays.toString(row));
                        }
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return prList;
    }
    
}
