package com.trilogyed.Customerservice.dao;

import com.trilogyed.Customerservice.model.Customer;

import java.util.List;

public interface CustomerDao {

    public Customer createCustomer(Customer customer);
    public Customer getCustomer(int id);
    public List<Customer> getAllCustomers();
   // public List<Customer> getCustomersByCategory(String category);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(int id);
}
