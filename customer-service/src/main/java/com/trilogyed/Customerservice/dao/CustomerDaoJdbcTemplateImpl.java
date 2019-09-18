package com.trilogyed.Customerservice.dao;

import com.trilogyed.Customerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao {

    private static final String INSERT_CUSTOMER_SQL =
            "insert into customer (first_name, last_name, street, city, zip, email, phone) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_CUSTOMER_BY_ID_SQL =
            "select * from customer where customer_id = ?";
    private static final String SELECT_ALL_CUSTOMERS_SQL =
            "select * from customer";

    private static final String UPDATE_CUSTOMER_SQL =
            "update customer set first_name = ?, last_name = ?, street = ?, city = ?, zip = ?, email = ?, phone = ? where customer_id = ?";
    private static final String DELETE_CUSTOMER_SQL =
            "delete from customer where customer_id = ?";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CustomerDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Customer createCustomer(Customer customer) {
        jdbcTemplate.update(INSERT_CUSTOMER_SQL,
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getStreet(),
                customer.getCity(),
                customer.getZip(),
                customer.getEmail(),
                customer.getPhone());


        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        customer.setCustomer_id(id);
        return customer;

    }

    @Override
    public Customer getCustomer(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CUSTOMER_BY_ID_SQL, this::mapRowToCustomer, id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    @Override
    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query(SELECT_ALL_CUSTOMERS_SQL, this :: mapRowToCustomer);
    }


    @Override
    public void updateCustomer(Customer customer) {
        jdbcTemplate.update(UPDATE_CUSTOMER_SQL,
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getStreet(),
                customer.getCity(),
                customer.getZip(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCustomer_id());

    }

    @Override
    public void deleteCustomer(int id) {
        jdbcTemplate.update(DELETE_CUSTOMER_SQL, id);

    }

    private Customer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException {
        Customer m1 = new Customer();
        m1.setCustomer_id(rs.getInt("customer_id"));
        m1.setFirst_name(rs.getString("first_name"));
        m1.setLast_name(rs.getString("last_name"));
        m1.setStreet(rs.getString("street"));
        m1.setCity(rs.getString("city"));
        m1.setZip(rs.getString("zip"));
        m1.setEmail(rs.getString("email"));
        m1.setPhone(rs.getString("phone"));
        return m1;
    }
}
