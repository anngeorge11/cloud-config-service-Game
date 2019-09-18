package com.trilogyed.Customerservice.dao;

import com.trilogyed.Customerservice.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoTests {


    @Autowired
    protected CustomerDao dao;

    @Before
    public void setUp() throws Exception {
        // clean out the test db
        List<Customer> mList = dao.getAllCustomers();

        mList.stream()
                .forEach(customer -> dao.deleteCustomer(customer.getCustomer_id()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteCustomer() {
        Customer customer = new Customer();
        customer.setFirst_name("John");
        customer.setLast_name("Mathew");
        customer.setStreet("One Pampered Chef ln");
        customer.setCity("Addison");
        customer.setZip("60101");
        customer.setEmail("john.mathew@gmail.com");
        customer.setPhone("2244325678");

        customer = dao.createCustomer(customer);

        Customer customer2 = dao.getCustomer(customer.getCustomer_id());
        assertEquals(customer.toString(), customer2.toString());
        dao.deleteCustomer(customer.getCustomer_id());

        customer2 = dao.getCustomer(customer.getCustomer_id());
        assertNull(customer2);

    }

    @Test
    public void getAllCustomers() {

        Customer customer = new Customer();
        customer.setFirst_name("John");
        customer.setLast_name("Mathew");
        customer.setStreet("One Pampered Chef ln");
        customer.setCity("Addison");
        customer.setZip("60101");
        customer.setEmail("john.mathew@gmail.com");
        customer.setPhone("2244325678");

        customer = dao.createCustomer(customer);

        List<Customer> customerList = dao.getAllCustomers();

        assertEquals(customerList.size(), 1);
    }


    @Test
    public void updateCustomer() {

        Customer customer = new Customer();
        customer.setFirst_name("John");
        customer.setLast_name("Mathew");
        customer.setStreet("One Pampered Chef ln");
        customer.setCity("Addison");
        customer.setZip("60101");
        customer.setEmail("john.mathew@gmail.com");
        customer.setPhone("2244325678");

        customer = dao.createCustomer(customer);

        customer.setEmail("john.mathew@yahoo.com");
        dao.updateCustomer(customer);

        Customer customer1 = dao.getCustomer(customer.getCustomer_id());
        assertEquals(customer1.toString(), customer.toString());


    }
}
