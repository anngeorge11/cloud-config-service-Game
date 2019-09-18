package com.trilogyed.invoiceservice.controller;

import com.trilogyed.invoiceservice.model.Invoice;
import com.trilogyed.invoiceservice.service.InvoiceServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RefreshScope
public class InvoiceController {

    @Autowired
    InvoiceServiceLayer service;



    public InvoiceController(InvoiceServiceLayer service)
    {

        this.service = service;
    }


    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody @Valid Invoice invoice) {

        return service.createInvoice(invoice);
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoice(@PathVariable @Valid int id) {

        return service.getInvoice(id);
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return service.getAllInvoices();

    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Invoice updateInvoice(@PathVariable("id") int id, @RequestBody @Valid Invoice invoice) {

        return service.updateInvoice(id,invoice);
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    public void deleteInvoice(@PathVariable @Valid int id) {

        service.deleteInvoice(id);
    }

}
