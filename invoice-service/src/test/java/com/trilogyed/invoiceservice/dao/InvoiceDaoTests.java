package com.trilogyed.invoiceservice.dao;


import com.trilogyed.invoiceservice.model.Invoice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTests {

    @Autowired
    protected InvoiceDao dao;

    @Before
    public void setUp() throws Exception {
        // clean out the test db
        List<Invoice> mList = dao.getAllInvoices();

        mList.stream()
                .forEach(invoice -> dao.deleteInvoice(invoice.getInvoiceId()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019,1,25));
        invoice = dao.createInvoice(invoice);

        Invoice invoice2 = dao.getInvoice(invoice.getInvoiceId());
        assertEquals(invoice.toString(), invoice2.toString());
        dao.deleteInvoice(invoice.getInvoiceId());
        invoice2 = dao.getInvoice(invoice.getInvoiceId());
        assertNull(invoice2);

    }

    @Test
    public void getAllInvoices() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019,1,25));
        invoice = dao.createInvoice(invoice);

        List<Invoice> invoiceList = dao.getAllInvoices();

        assertEquals(invoiceList.size(), 1);
    }


    @Test
    public void updateInvoices() {

        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019,1,25));
        invoice = dao.createInvoice(invoice);

        invoice.setCustomerId(2);

        dao.updateInvoice(invoice);

        Invoice invoice1 = dao.getInvoice(invoice.getInvoiceId());
        assertEquals(invoice1.toString(), invoice.toString());


    }

}
