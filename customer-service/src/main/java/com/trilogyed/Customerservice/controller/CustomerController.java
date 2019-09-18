package com.trilogyed.Customerservice.controller;

import com.trilogyed.Customerservice.model.Customer;
import com.trilogyed.Customerservice.service.CustomerServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RefreshScope
public class CustomerController {


    @Autowired
    CustomerServiceLayer service;



    public CustomerController(CustomerServiceLayer service)
    {
        this.service = service;
    }


    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody @Valid Customer customer) {

        return service.createCustomer(customer);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable @Valid int id) {

        return service.getCustomer(id);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
      return service.getAllCustomers();

    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Customer updateCustomer(@PathVariable("id") int id, @RequestBody @Valid Customer customer) {

        return service.updateCustomer(id,customer);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable @Valid int id) {

        service.deleteCustomer(id);
    }



}
