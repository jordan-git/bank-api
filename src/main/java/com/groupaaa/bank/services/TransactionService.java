package com.groupaaa.bank.services;


public class TransactionService {
    private static final TransactionService instance = new TransactionService();
    
    private TransactionService() {
    }
    
    // singleton pattern
    public static TransactionService getService() {
        return instance;
    }
}
