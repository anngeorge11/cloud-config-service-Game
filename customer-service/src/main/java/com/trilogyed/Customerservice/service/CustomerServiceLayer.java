package com.trilogyed.Customerservice.service;

import com.trilogyed.Customerservice.dao.CustomerDao;
import com.trilogyed.Customerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Component
public class CustomerServiceLayer {

    CustomerDao dao;


    @Autowired
    public CustomerServiceLayer(CustomerDao dao) {
        this.dao = dao;
    }

    public Customer createCustomer(Customer customer) {
        dao.createCustomer(customer);
        return customer;
    }

    public Customer getCustomer(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("Customer id should be greater than 0");

        }
        return dao.getCustomer(id);
    }

    public List<Customer> getAllCustomers() {

        return dao.getAllCustomers();
    }

    public Customer updateCustomer(int id, Customer customer) {
        List<Customer> cusList = dao.getAllCustomers();
        for (Customer c : cusList) {
            if (c.getCustomer_id() == id) {
                customer.setCustomer_id(id);

                dao.updateCustomer(customer);

                if (id != customer.getCustomer_id()) {
                    throw new IllegalArgumentException("Customer ID on path must match the ID in the Customer object");

                }
            }
        }
        return dao.getCustomer(id);

    }

    public void deleteCustomer (int id) {

        if (id != dao.getCustomer(id).getCustomer_id()) {
            throw new IllegalArgumentException("Customer ID on path must match the ID in the Customer object.");
        }

        dao.deleteCustomer(id);
    }


}
