package com.groupaaa.bank.services;

import com.groupaaa.bank.models.Transaction;
import java.util.List;


public class TransactionService {
    private static final TransactionService instance = new TransactionService();
    private static final CustomerService customerService = CustomerService.getService();
    
    private TransactionService() {
    }
    
    // singleton pattern
    public static TransactionService getService() {
        return instance;
    }

    public List<Transaction> getTransactions(int customerId, int accountNo) {
        return customerService.getCustomer(customerId).getAccount(accountNo).getTransactions();
    }

    public Transaction getTransaction(int customerId, int accountNo, int transactionId) {
       return customerService.getCustomer(customerId).getAccount(accountNo).getTransaction(transactionId);
    }

    public void addTransaction(int customerId, int accountNo, Transaction transaction) {
        customerService.getCustomer(customerId).getAccount(accountNo).addTransaction(transaction);
    }
    
    public Transaction updateTransaction(int customerId, int accountNo, int transactionId, Transaction updatedTransaction) {
        return customerService.getCustomer(customerId).getAccount(accountNo).setTransaction(transactionId, updatedTransaction);
    }
    
    public Transaction deleteTransaction(int customerId, int accountNo, int transactionId) {
        return customerService.getCustomer(customerId).getAccount(accountNo).deleteTransaction(transactionId);
    }
}
