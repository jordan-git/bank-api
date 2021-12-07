package com.groupaaa.bank.services;

import com.groupaaa.bank.models.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private static final CustomerService instance = new CustomerService();
    private final List<Customer> customers;

    private CustomerService() {
        customers = new ArrayList<>();

        Customer c1 = new Customer("John Doe", "6 Wilson Rd, Dublin 4", "johndoe@mail.ie", "1234");
        customers.add(c1);
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
}