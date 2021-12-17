package com.groupaaa.bank.models;

import java.util.ArrayList;
import java.util.List;


public class Account {
    private String sortCode;
    private int accountNo;
    private double balance;
    private final List<Transaction> transactions;
    
    private static int nextAccountNo = 1;


    
    
    public Account(String sortCode, double balance) {
        this.sortCode = sortCode;
        this.balance = balance;
        
        this.accountNo = nextAccountNo++;
        
        transactions = new ArrayList<>();
    }
    
    public static int getNextAccountNo() {
        return nextAccountNo++;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    public Transaction setTransaction(int transactionId, Transaction updatedTransaction) {
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            
            if (transaction.getTransactionId() == transactionId) {
                transactions.set(i, updatedTransaction);
                return updatedTransaction;
            }
        }
        return null;
    }
    
    public Transaction getTransaction(int transactionId) {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionId() == transactionId) {
                return transaction;
            }
        }
        
        return null;
    }
    
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        setBalance(transaction.getNewBalance());
    }
    
    public Transaction deleteTransaction(int transactionId) {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionId() == transactionId) {
                transactions.remove(transaction);
                return transaction;
            }
        }
        
        return null;
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
