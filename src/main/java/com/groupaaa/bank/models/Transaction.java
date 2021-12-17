package com.groupaaa.bank.models;

import java.time.LocalDate;


public class Transaction {
    private static int nextTransactionId = 1;
    private String type, description;
    private LocalDate date;
    private double newBalance;
    public int transactionId;

   
    
    
    public Transaction(){
        
    }
    
    public Transaction(String type, String description, double newBalance) {
        this.transactionId = nextTransactionId++;
        this.type = type;
        this.description = description;
        this.newBalance = newBalance;
        date = LocalDate.now();
       
    }
    
    public static int getNextTransactionId() {
        return nextTransactionId;
    }

    public static void setNextTransactionId(int nextTransactionId) {
        Transaction.nextTransactionId = nextTransactionId;
    }
    
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(double newBalance) {
        this.newBalance = newBalance;
    }

    
}
