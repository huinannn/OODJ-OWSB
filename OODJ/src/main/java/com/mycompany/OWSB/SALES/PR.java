/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OWSB.SALES;

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
 * @author TP070386
 */
public class PR {
    //Fixed selection for PR Status
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
            for(PR_Status stat : PR_Status.values()){
                return stat;
            }
            return null;
        }
    }
    private String PRID;
    private String itemCode;
    private String itemName;
    private int quantity;
    private String supplierID;
    private PR_Status status;
    private String raisedBy;
    private String date;
    
    //Empty Constructor
    public PR(){}
    
    //Constructor
    public PR(String PRID, String itemCode, String itemName, int quantity, String supplierID, PR_Status status, String raisedBy, String date){
        this.PRID = PRID;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.supplierID = supplierID;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
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

    public void setRaisedBy(String raisedBy) {
        this.raisedBy = raisedBy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String generateNextPRID(){
        List<PR> purchase_requisition = PR.viewPRsInFile();
        int maxID = 0;
        
        for(PR pr : purchase_requisition){
            String id = pr.getPRID();
            if(id.startsWith("PR")){
                try{
                    int numericPart = Integer.parseInt(id.substring(2));
                    if (numericPart > maxID){
                        maxID = numericPart;
                    }
                } catch (NumberFormatException e){
                    
                }
            }
        }
        
        int nextID = maxID +1;
        return String.format("PR%03d", nextID);
    }
    
    public static void savePRToFile(PR pr){
        try {
            //Get Path
            String classPath = PR.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(),"database");
            if(!dbDir.exists()){
                dbDir.mkdirs();
            }
            
            File file = new File(dbDir, "PR.txt");
            boolean fileExists = file.exists();
            
            //Write File (Append)
            try (
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
            ) {
                if (!fileExists){
                    bw.write("PRID;ItemCode;ItemName;Quantity;SupplierID;Status;RaisedBy;RequiredDeliveryDate");
                    bw.newLine();
                }
                
                bw.write(pr.getPRID() + ";" +
                        pr.getItemCode() + ";" +
                        pr.getItemName() + ";" +
                        pr.getQuantity() + ";" +
                        pr.getSupplierID() + ";" +
                        pr.getStatus() + ";" +
                        pr.getRaisedBy() + ";" +
                        pr.getDate());
                bw.newLine();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                BufferedReader br = new BufferedReader(fr)
            ){
                String line;
                boolean isFirstLine = true;
                
                while((line = br.readLine()) != null){
                    if (isFirstLine){
                        isFirstLine = false;
                        continue;
                    }
                    
                    if (!line.trim().isEmpty()){
                        String[] row = line.split(";");
                        if (row.length >= 8){
                            PR_Status status = PR_Status.fromString(row[5]);
                            int quantity = Integer.parseInt(row[3]);
                            PR pr = new PR(
                               row[0],
                               row[1],
                               row[2],
                               quantity,
                               row[4],
                               status,
                               row[6],
                               row[7]
                            );
                            prList.add(pr);
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
    
    public static PR getPRByID(String PRID){
        try{
            String classPath = PR.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File baseDir = new File(classPath).getParentFile();
            File dbDir = new File(baseDir.getParentFile(), "database");
            File file = new File(dbDir, "PR.txt");

            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "PR.txt file does not exist.");
                return null;
            }
            
            try (BufferedReader br = new BufferedReader(new FileReader(file))){
                String line;
                boolean isFirstLine = true;
                
                while((line = br.readLine()) != null){
                    if (isFirstLine){
                        isFirstLine = false;
                        continue;
                    }
                    
                    String[] parts = line.split(";");
                    if (parts.length >= 8 && parts[0].equals(PRID)){
                        String itemCode = parts[1];
                        String itemName = parts[2];
                        int quantity = Integer.parseInt(parts[3]);
                        String supplierID = parts[4];
                        PR_Status status = PR_Status.fromString(parts[5]);
                        String raisedBy = parts[6];
                        String date = parts[7];
                        
                        return new PR(PRID, itemCode, itemName, quantity, supplierID, status, raisedBy, date);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading item: " + e.getMessage());
        }
        
        return null;
    }
    
//    public List<PR> findByItemCode(String itemCode){
//        
//    }
    
    public static void editPRInFile(String PRID, PR updatedPR){
        try {
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
                while((line = br.readLine()) != null){
                    if (isFirstLine){
                        lines.add(line);
                        isFirstLine = false;
                        continue;
                    }
                    
                    String[] parts = line.split(";");
                    if (parts[0].equals(PRID)) {
                        String updatedLine = updatedPR.getPRID() + ";" +
                                updatedPR.getItemCode() + ";" +
                                updatedPR.getItemName() + ";" +
                                updatedPR.getQuantity() + ";" +
                                updatedPR.getSupplierID() + ";" +
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
            
            JOptionPane.showMessageDialog(null, "PR updated successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error editing item: " + e.getMessage());
        }
    }
    
    public static void deletePRInFile(String PRID){
        try {
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
                    if (!parts[0].equals(PRID)){
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
            JOptionPane.showMessageDialog(null, "Error deleting item: " + e.getMessage());
        }
    } 
}
