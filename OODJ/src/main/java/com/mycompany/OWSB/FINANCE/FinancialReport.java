/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.OWSB.FINANCE;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *
 * @author Thong Hui Nan
 */

public class FinancialReport {
    private String reportId;
    private String year;
    private Map<String, Double> salesMap;
    private Map<String, Double> paymentMap;
    private String preparedBy;
    private LocalDate date;
    
    public enum MonthEnum {
        January, February, March, April, May, June, July, August, September, October, November, December
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Map<String, Double> getSalesMap() {
        return salesMap;
    }

    public void setSalesMap(Map<String, Double> salesMap) {
        this.salesMap = salesMap;
    }

    public Map<String, Double> getPaymentMap() {
        return paymentMap;
    }

    public void setPaymentMap(Map<String, Double> paymentMap) {
        this.paymentMap = paymentMap;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public static class SalesRecord {
        public String itemCode;
        public String itemName;
        public int quantitySold;
        public double totalPrice;

        public SalesRecord(String itemCode, String itemName, int quantitySold, double totalPrice) {
            this.itemCode = itemCode;
            this.itemName = itemName;
            this.quantitySold = quantitySold;
            this.totalPrice = totalPrice;
        }
    }

    public static class ReceiptRecord {
        String itemCode;
        String itemName;
        int quantity;
        double totalPrice;

        public ReceiptRecord(String itemCode, String itemName, int quantity, double totalPrice) {
            this.itemCode = itemCode;
            this.itemName = itemName;
            this.quantity = quantity;
            this.totalPrice = totalPrice;
        }
    }

    public Map<String, String> loadInventoryItemNames() {
        Map<String, String> itemNames = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("database/inventory.txt"))) {
            String line = br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 2) {
                    itemNames.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemNames;
    }

    public List<SalesRecord> getSalesForMonth(String selectedMonth, String yearInput) {
        List<SalesRecord> salesList = new ArrayList<>();
        Map<String, String> itemNames = loadInventoryItemNames();

        String targetPrefix = yearInput + "-" + String.format("%02d", MonthEnum.valueOf(selectedMonth).ordinal() + 1);

        try (BufferedReader br = new BufferedReader(new FileReader("Database/DailySales.txt"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 5) {
                    String itemCode = parts[1];
                    int quantitySold = Integer.parseInt(parts[2]);
                    double totalPrice = Double.parseDouble(parts[3]);
                    String date = parts[4];

                    if (date.startsWith(targetPrefix)) {
                        String itemName = itemNames.getOrDefault(itemCode, "Unknown");
                        salesList.add(new SalesRecord(itemCode, itemName, quantitySold, totalPrice));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salesList;
    }
    
    public List<ReceiptRecord> getReceiptForMonth(String selectedMonth, String yearInput) {
        List<ReceiptRecord> receiptList = new ArrayList<>();
        Map<String, String> itemNames = loadInventoryItemNames();
        String targetPrefix = yearInput + "-" + String.format("%02d", MonthEnum.valueOf(selectedMonth).ordinal() + 1);

        try (BufferedReader br = new BufferedReader(new FileReader("Receipt.txt"))) {
            String line;
            String date = "";
            String itemCode = "";
            int quantity = 0;
            double totalPrice = 0;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("Date")) {
                    date = line.split(":")[1].trim();
                } else if (line.startsWith("Item")) {
                    String[] parts = line.split(":")[1].trim().split(" - ");
                    itemCode = parts[0];
                } else if (line.startsWith("Quantity")) {
                    quantity = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Total Price")) {
                    totalPrice = Double.parseDouble(line.split(": RM ")[1].trim());

                    if (date.startsWith(targetPrefix)) {
                        String itemName = itemNames.getOrDefault(itemCode, "Unknown");
                        receiptList.add(new ReceiptRecord(itemCode, itemName, quantity, totalPrice));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiptList;
    }
    
    public Map<String, Double> getMonthlySalesForYear(String selectedYear) {
        Map<String, Double> salesMap = new LinkedHashMap<>();
        for (FinancialReport.MonthEnum month : FinancialReport.MonthEnum.values()) {
            salesMap.put(month.name(), 0.0);
        }

        try (BufferedReader br = new BufferedReader(new FileReader("Database/DailySales.txt"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 5) {
                    String date = parts[4];
                    if (date.startsWith(selectedYear)) {
                        int monthIndex = Integer.parseInt(date.substring(5, 7)) - 1;
                        String monthName = FinancialReport.MonthEnum.values()[monthIndex].name();
                        double total = Double.parseDouble(parts[3]);
                        salesMap.put(monthName, salesMap.get(monthName) + total);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salesMap;
    }
    
   public Map<String, Double> getMonthlyPaymentsForYear(String selectedYear) {
        Map<String, Double> paymentMap = new LinkedHashMap<>();
        for (FinancialReport.MonthEnum month : FinancialReport.MonthEnum.values()) {
            paymentMap.put(month.name(), 0.0);
        }

        try (BufferedReader br = new BufferedReader(new FileReader("Receipt.txt"))) {
            String line;
            String date = "";
            double totalPrice = 0.0;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("Date")) {
                    date = line.split(":")[1].trim();
                } else if (line.startsWith("Total Price")) {
                    totalPrice = Double.parseDouble(line.split(": RM ")[1].trim());

                    if (date.startsWith(selectedYear)) {
                        int monthIndex = Integer.parseInt(date.substring(5, 7)) - 1;
                        String monthName = FinancialReport.MonthEnum.values()[monthIndex].name();
                        paymentMap.put(monthName, paymentMap.get(monthName) + totalPrice);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return paymentMap;
   }
    
    public String toPrintableMonthlyString() {
        StringBuilder builder = new StringBuilder();
        builder.append("-----------------------------------------------------\n");
        builder.append("                MONTHLY FINANCE REPORT\n");
        builder.append("-----------------------------------------------------\n");
        builder.append(String.format("Report ID          : %s\n", reportId));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = (date != null) ? date.format(formatter) : "N/A";
        builder.append(String.format("Date               : %s\n", formattedDate));
        builder.append(String.format("Year               : %s\n\n", year));

        builder.append("------------------ Monthly Sales --------------------\n");
        for (String month : salesMap.keySet()) {
            builder.append(String.format("%-20s: RM %.2f\n", month, salesMap.get(month)));
        }

        builder.append("\n----------------- Monthly Payments ------------------\n");
        for (String month : paymentMap.keySet()) {
            builder.append(String.format("%-20s: RM %.2f\n", month, paymentMap.get(month)));
        }

        builder.append(String.format("\nPrepared By        : %s\n", preparedBy));
        builder.append("-----------------------------------------------------\n");
        builder.append("        Thank you. End of Monthly Finance Report.\n");
        builder.append("-----------------------------------------------------");

        return builder.toString();
    }
    
    public String toPrintableYearlyString() {
        StringBuilder builder = new StringBuilder();
        builder.append("=====================================================\n");
        builder.append("                YEARLY FINANCE REPORT\n");
        builder.append("=====================================================\n");
        builder.append(String.format("Report ID          : %s\n", reportId));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = (date != null) ? date.format(formatter) : "N/A"; 
        builder.append(String.format("Date               : %s\n", formattedDate));
        builder.append(String.format("Year               : %s\n\n", year));

        double totalSales = 0.0;
        for (double sales : salesMap.values()) {
            totalSales += sales;
        }
        double totalPayments = 0.0;
        for (double payments : paymentMap.values()) {
            totalPayments += payments;
        }

        builder.append(String.format("Total Sales        : RM %.2f\n", totalSales));
        builder.append(String.format("Total Payments     : RM %.2f\n", totalPayments));

        builder.append("\n------------------ Monthly Sales --------------------\n");
        for (String month : salesMap.keySet()) {
            builder.append(String.format("%-20s: RM %.2f\n", month, salesMap.get(month)));
        }

        builder.append("\n----------------- Monthly Payments ------------------\n");
        for (String month : paymentMap.keySet()) {
            builder.append(String.format("%-20s: RM %.2f\n", month, paymentMap.get(month)));
        }

        builder.append(String.format("\nPrepared By        : %s\n", preparedBy));
        builder.append("=====================================================\n");
        builder.append("         Thank you. End of Yearly Finance Report.\n");
        builder.append("=====================================================");

        return builder.toString();
    }

    public static String generateNewFinanceReportID() {
        int lastNum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("FinancialReport.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.matches(".*FREP\\d{3}.*")) {
                    int index = line.indexOf("FREP");
                    if (index != -1 && line.length() >= index + 7) {
                        String numStr = line.substring(index + 4, index + 7);
                        try {
                            int num = Integer.parseInt(numStr);
                            if (num > lastNum) lastNum = num;
                        } catch (NumberFormatException e) {}
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.format("FREP%03d", lastNum + 1);
    }

    public void saveToMonthlyFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("FinancialReport.txt", true))) {
            writer.println(toPrintableMonthlyString());
            writer.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void saveToYearlyFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("FinancialReport.txt", true))) {
            writer.println(toPrintableYearlyString());
            writer.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}