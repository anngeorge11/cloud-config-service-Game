package com.trilogyed.invoiceservice.service;

import com.trilogyed.invoiceservice.dao.InvoiceDao;
import com.trilogyed.invoiceservice.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class InvoiceServiceLayer {

    InvoiceDao dao;


    @Autowired
    public InvoiceServiceLayer(InvoiceDao dao) {

        this.dao = dao;
    }

    public Invoice createInvoice(Invoice invoice) {
        dao.createInvoice(invoice);
        return invoice;
    }

    public Invoice getInvoice(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("Invoice id should be greater than 0");

        }
        return dao.getInvoice(id);
    }

    public List<Invoice> getAllInvoices() {

        return dao.getAllInvoices();
    }

    public Invoice updateInvoice(int id, Invoice invoice) {
        List<Invoice> proList = dao.getAllInvoices();
        for (Invoice c : proList) {
            if (c.getInvoiceId() == id) {
                invoice.setInvoiceId(id);

                dao.updateInvoice(invoice);

                if (id != invoice.getInvoiceId()) {
                    throw new IllegalArgumentException("Invoice ID on path must match the ID in the Invoice object");

                }
            }
        }
        return dao.getInvoice(id);

    }

    public void deleteInvoice (int id) {

        if (id != dao.getInvoice(id).getInvoiceId()) {
            throw new IllegalArgumentException("Invoice ID on path must match the ID in the Invoice object.");
        }

        dao.deleteInvoice(id);
    }



}
