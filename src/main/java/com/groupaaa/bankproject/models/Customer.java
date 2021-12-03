package com.groupaaa.bankproject.models;

import java.util.List;
import java.util.ArrayList;

public class Customer {
    private String name, address, email, pin;
    private final List<Account> accounts;

    public Customer(String name, String address, String email, String pin) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.pin = pin; // Using 4 digit pin as credentials for simplicity sake

        accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
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
}
