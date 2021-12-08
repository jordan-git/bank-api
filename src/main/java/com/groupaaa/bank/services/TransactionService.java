package com.groupaaa.bank.services;

import com.groupaaa.bank.models.Transaction;
import java.util.List;


public class TransactionService {
    private static final TransactionService instance = new TransactionService();
    
    private TransactionService() {
    }
    
    // singleton pattern
    public static TransactionService getService() {
        return instance;
    }

    public List<Transaction> getAllTransactions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
