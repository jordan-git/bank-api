package com.groupaaa.bank.services;

import com.groupaaa.bank.models.Account;
import java.util.List;

public class AccountService {
    private static final AccountService instance = new AccountService();
    private final CustomerService customerService = CustomerService.getService();
    
    private AccountService() {
    }
    
    // singleton pattern
    public static AccountService getService() {
        return instance;
    }
    
    public List<Account> getAccountsByCustomerId(int id) {
        return customerService.getCustomer(id).getAccounts();
    }

    public Object getAccountBalance(int customerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
