package com.trilogyed.invoiceservice.dao;

import com.trilogyed.invoiceservice.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {

    private static final String INSERT_INVOICE_SQL =
            "insert into invoice (customer_id, purchase_date) values (?, ?)";
    private static final String SELECT_INVOICE_BY_ID_SQL =
            "select * from invoice where invoice_id = ?";
    private static final String SELECT_ALL_INVOICES_SQL =
            "select * from invoice";

    private static final String UPDATE_INVOICE_SQL =
            "update invoice set customer_id = ?, purchase_date = ? where invoice_id = ?";
    private static final String DELETE_INVOICE_SQL =
            "delete from invoice where invoice_id = ?";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        jdbcTemplate.update(INSERT_INVOICE_SQL,
                invoice.getCustomerId(),
                invoice.getPurchaseDate());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        invoice.setInvoiceId(id);
        return invoice;
    }

    @Override
    public Invoice getInvoice(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_BY_ID_SQL, this::mapRowToInvoice, id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this :: mapRowToInvoice);

    }

    @Override
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL,
                invoice.getCustomerId(),
                invoice.getPurchaseDate(),
                invoice.getInvoiceId());


    }

    @Override
    public void deleteInvoice(int id) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, id);

    }

    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice m1 = new Invoice();
        m1.setInvoiceId(rs.getInt("invoice_id"));
        m1.setCustomerId(rs.getInt("customer_id"));
        m1.setPurchaseDate(rs.getDate("purchase_date").toLocalDate());
        return m1;
    }
}
