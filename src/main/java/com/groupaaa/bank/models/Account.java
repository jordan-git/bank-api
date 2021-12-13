package com.groupaaa.bank.models;

import java.util.ArrayList;
import java.util.List;


public class Account {
    private String sortCode;
    private int accountNo;
    private double balance;
    private final List<Transaction> transactions;


    
    
    public Account(String sortCode, int accountNo, double balance) {
        this.sortCode = sortCode;
        this.accountNo = accountNo;
        this.balance = balance;
        
        transactions = new ArrayList<>();
    }
    
    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    
    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
}
