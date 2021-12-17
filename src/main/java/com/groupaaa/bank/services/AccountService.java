package com.groupaaa.bank.services;

import com.groupaaa.bank.models.Account;
import com.groupaaa.bank.models.Customer;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    public List<Account> getAccounts(int customerId) {
        return customerService.getCustomer(customerId).getAccounts();
    }
    
    public Account getAccount(int accountNo) {
        List<Customer> customers = customerService.getCustomers();
        
        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNo() == accountNo)
                    return account;
            }
        }
        
        return null;
    }
    
    public Account getAccount(int customerId, int accountNo) {
        return customerService.getCustomer(customerId).getAccount(accountNo);   
    }
    
    public Account updateAccount(int customerId, int accountNo, Account account) {
        return customerService.getCustomer(customerId).setAccount(accountNo, account);
    }
    
    public Account deleteAccount(int customerId, int accountNo) {
        return customerService.getCustomer(customerId).deleteAccount(accountNo);
    }

    public Object getAccountBalance(int customerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Account updateAccountByID(Account account, int accountId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Account deleteAccountByID(Account account, int accountId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
