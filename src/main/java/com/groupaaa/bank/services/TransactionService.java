package com.groupaaa.bank.services;

import com.groupaaa.bank.models.Account;
import com.groupaaa.bank.models.Customer;
import com.groupaaa.bank.models.Transaction;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.List;


public class TransactionService {
    private static final TransactionService instance = new TransactionService();
    
    private List<Transaction> transactions;
    
    
    private TransactionService() {
//        Transaction t2 = new Transaction("Debit", "Dunnes Stores", 300);
//        Transaction t3 = new Transaction("Debit", "Harvey Norman", 200);
//        Transaction t4 = new Transaction("Debit", "Tesco", 5);
//        Transaction t5 = new Transaction("Debit", "Newsagents", 3);
//        //Creating new customer and account
//        Customer c5 = new Customer("Matt Hardy", "55 Springfield, D11", "MH@mail.ie", "4444");
//        Account a5 = new Account("sort", 4325);
//        //Adding transaction to account and account to customer
//        a5.addTransaction(t2);
//        c5.addAccount(a5);
//        //Adding all transactions to transaction array
//        transactions.addAll(Arrays.asList(t2, t3, t4, t5));

    }
    
    // singleton pattern
    public static TransactionService getService() {
        return instance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Transaction getTransactionById(int transactionId) {
       for(Transaction transaction: transactions){
           if(transaction.transactionId == transactionId){
               return transaction;
           }
       }
       return null;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    
    public Transaction updateTransaction(int transactionId, Transaction updatedTransaction) {
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            
            if (transaction.getTransactionId() == transactionId) {
                transactions.set(i, updatedTransaction);
                return updatedTransaction;
            }
        }
        
        return null;
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
}
