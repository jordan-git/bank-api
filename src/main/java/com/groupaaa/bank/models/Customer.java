package com.groupaaa.bank.models;

import java.util.List;
import java.util.ArrayList;

public class Customer {
    private String name, address, email, pin;
    private int id;
    private List<Account> accounts;
    
    private static int nextCustomerId = 1;

    public Customer(String name, String address, String email, String pin) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.pin = pin; // Using 4 digit pin as credentials for simplicity sake
        this.id = nextCustomerId++;

        accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }
    
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    
    public Account setAccount(int accountNo, Account account) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNo() == accountNo) {
                accounts.set(i, account);
                return account;
            }
        }
        
        return null;
    }
    
    public Account getAccount(int accountNo) {
        for (Account account : accounts) {
            if (account.getAccountNo() == accountNo) {
                return account;
            }
        }
        
        return null;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
    
    public Account deleteAccount(int accountNo) {
        for (Account account : accounts) {
            if (account.getAccountNo() == id) {
                accounts.remove(account);
                return account;
            }
        }
        
        return null;
    }
    
    public static int getNextAccountId() {
        return nextCustomerId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
