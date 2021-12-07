package com.groupaaa.bank.services;

import java.util.List;
import javax.transaction.Transaction;


public class TransactionService {
    private static final TransactionService instance = new TransactionService();
    
    public TransactionService() {
    }
    
    // singleton pattern
    public static TransactionService getService() {
        return instance;
    }

    public List<Transaction> getAllTransactions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
