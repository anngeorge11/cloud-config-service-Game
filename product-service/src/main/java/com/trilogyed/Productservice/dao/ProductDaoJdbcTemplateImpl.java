package com.trilogyed.Productservice.dao;

import com.trilogyed.Productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDaoJdbcTemplateImpl implements ProductDao {

    private static final String INSERT_PRODUCT_SQL =
            "insert into product (product_name, product_description, list_price, unit_cost) values (?, ?, ?, ?)";
    private static final String SELECT_PRODUCT_BY_ID_SQL =
            "select * from product where product_id = ?";
    private static final String SELECT_ALL_PRODUCTS_SQL =
            "select * from product";

    private static final String UPDATE_PRODUCT_SQL =
            "update product set product_name = ?, product_description = ?, list_price = ?, unit_cost = ? where product_id = ?";
    private static final String DELETE_PRODUCT_SQL =
            "delete from product where product_id = ?";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProductDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product createProduct(Product product) {
        jdbcTemplate.update(INSERT_PRODUCT_SQL,
                product.getProduct_name(),
                product.getProduct_description(),
                product.getList_price(),
                product.getUnit_cost());


        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        product.setProduct_id(id);
        return product;
    }

    @Override
    public Product getProduct(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_PRODUCT_BY_ID_SQL, this::mapRowToProduct, id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return jdbcTemplate.query(SELECT_ALL_PRODUCTS_SQL, this :: mapRowToProduct);
    }

    @Override
    public void updateProduct(Product product) {
        jdbcTemplate.update(UPDATE_PRODUCT_SQL,
                product.getProduct_name(),
                product.getProduct_description(),
                product.getList_price(),
                product.getUnit_cost(),
                product.getProduct_id());


    }

    @Override
    public void deleteProduct(int id) {
        jdbcTemplate.update(DELETE_PRODUCT_SQL, id);

    }

    private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
        Product m1 = new Product();
        m1.setProduct_id(rs.getInt("product_id"));
        m1.setProduct_name(rs.getString("product_name"));
        m1.setProduct_description(rs.getString("product_description"));
        m1.setList_price(rs.getBigDecimal("list_price"));
        m1.setUnit_cost(rs.getBigDecimal("unit_cost"));
        return m1;
    }
}
