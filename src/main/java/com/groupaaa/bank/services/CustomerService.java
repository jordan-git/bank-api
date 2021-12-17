package com.groupaaa.bank.services;

import com.groupaaa.bank.models.Account;
import com.groupaaa.bank.models.Customer;
import com.groupaaa.bank.models.Transaction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerService {
    private static final CustomerService instance = new CustomerService();
    private final List<Customer> customers;

    private CustomerService() {
        customers = new ArrayList<>();

        Customer c1 = new Customer("John Doe", "55 Spring Park, Kildare", "johndoe@mail.ie", "1234");
        Customer c2 = new Customer("Mike Johnson", "14 Blackroad, Wicklow", "johndoe@mail.ie", "2345");
        Customer c3 = new Customer("Rebecca White", "6 Wilson Rd, Dublin 4", "johndoe@mail.ie", "3456");
        Customer c4 = new Customer("Peter Burn", "12 Rowe Rd, Dublin 6", "johndoe@mail.ie", "4567");
        
        Account a1 = new Account("903111", 50);
        Account a2 = new Account("903111", 100);
        Account a3 = new Account("903111", 150);
        Account a4 = new Account("903111", 5550);
        
        Transaction t1 = new Transaction("DEBIT", "Deposit at branch", 10);
        Transaction t2 = new Transaction("DEBIT", "Deposit at branch", 100);
        Transaction t3 = new Transaction("CREDIT", "Deposit at branch", 200);
        
        a1.addTransaction(t1);
        a2.addTransaction(t2);
        a3.addTransaction(t3);
        
        c1.addAccount(a1);
        c1.addAccount(a4);
        
        c2.addAccount(a2);
        c3.addAccount(a3);
        
        customers.addAll(Arrays.asList(c1, c2, c3, c4));
    }

    // singleton pattern
    public static CustomerService getService() {
        return instance;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
    
    public Customer getCustomer(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        
        return null;
    }
    
    public Customer updateCustomer(int id, Customer updatedCustomer) {
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            
            if (customer.getId() == id) {
                customers.set(i, updatedCustomer);
                return updatedCustomer;
            }
        }
        
        return null;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    
    public Customer deleteCustomer(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customers.remove(customer);
                return customer;
            }
        }

        return null;
    }
}