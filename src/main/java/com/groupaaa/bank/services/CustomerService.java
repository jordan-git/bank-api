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
        
        Account a1 = new Account("sort", 1234, 50);
        Transaction t1 = new Transaction("test", "testdesc", 0);
        
        a1.addTransaction(t1);
        c1.addAccount(a1);
        
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

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    
    // Need to fix, doesn't delete for some reason
//    public Customer deleteCustomer(int id) {
//        for (Customer customer : customers) {
//            if (customer.getId() == id) {
//                customers.remove(customer);
//                return customer;
//            }
//        }
//
//        return null;
//    }
}