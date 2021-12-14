package com.groupaaa.bank.services;

import com.groupaaa.bank.models.Transaction;
import static java.util.Collections.list;
import java.util.List;


public class TransactionService {
    private static final TransactionService instance = new TransactionService();
    private final CustomerService customerService = CustomerService.getService();
    
    private TransactionService() {
    }
    
    // singleton pattern
    public static TransactionService getService() {
        return instance;
    }

    public List<Transaction> getAllTransactions(int customerId, int accountId) {
        return customerService.getCustomer(customerId).getAccount(accountId).getTransactions();
    }

    public Transaction getTransaction(int transactionId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
