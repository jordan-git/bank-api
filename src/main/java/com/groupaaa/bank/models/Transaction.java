package com.groupaaa.bank.models;

import java.time.LocalDate;


public class Transaction {
    private String type, description;
    private LocalDate date;
    private double balance;
    private double newBalance;
    private int transactionID;
    private long amount;
    private String transactionAnotherAccount;
    private String transactionBetweenMyAccounts;
    private String transactionToName;
    private String transactionFromName;
    
    
    public Transaction(String type, String description, double newBalance, double balance) {
        this.transactionID = transactionID;
        this.type = type;
        this.description = description;
        this.balance = balance;
        this.newBalance = newBalance;
        this.amount = amount;
        date = LocalDate.now();
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

   

    public String getTransactionToName() {
        return transactionToName;
    }

    public void setTransactionToName(String transactionToName) {
        this.transactionToName = transactionToName;
    }

    public String getTransactionFromName() {
        return transactionFromName;
    }

    public void setTransactionFromName(String transactionFromName) {
        this.transactionFromName = transactionFromName;
    }

    public String getTransactionAnotherAccount() {
        return transactionAnotherAccount;
    }

    public void setTransactionAnotherAccount(String transactionAnotherAccount) {
        this.transactionAnotherAccount = transactionAnotherAccount;
    }

    public String getTransactionBetweenMyAccounts() {
        return transactionBetweenMyAccounts;
    }

    public void setTransactionBetweenMyAccounts(String transactionBetweenMyAccounts) {
        this.transactionBetweenMyAccounts = transactionBetweenMyAccounts;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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
