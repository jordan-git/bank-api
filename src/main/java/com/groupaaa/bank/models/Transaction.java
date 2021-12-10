package com.groupaaa.bank.models;

import java.time.LocalDate;


public class Transaction {
    private String type, description;
    private LocalDate date;
    private double balance;
    private double newBalance;
    private int transactionID;
    private long amount;
    
   
    
    
    
    public Transaction(String type, String description, double newBalance) {
        this.transactionID = transactionID;
        this.type = type;
        this.description = description;
        this.newBalance = newBalance;
        this.amount = amount;
        date = LocalDate.now();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    
    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
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
