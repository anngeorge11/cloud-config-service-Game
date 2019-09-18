package com.trilogyed.Inventoryservice.dao;

import com.trilogyed.Inventoryservice.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InventoryDaoJdbcTemplateImpl implements InventoryDao {

    private static final String INSERT_INVENTORY_SQL =
            "insert into inventory (product_id, quantity) values (?, ?)";
    private static final String SELECT_INVENTORY_BY_ID_SQL =
            "select * from inventory where inventory_id = ?";
    private static final String SELECT_ALL_INVENTORIES_SQL =
            "select * from inventory";
    private static final String SELECT_INVENTORY_BY_PRODUCTID_SQL =
            "select * from inventory where product_id = ?";

    private static final String UPDATE_INVENTORY_SQL =
            "update inventory set product_id = ?, quantity = ? where inventory_id = ?";
    private static final String DELETE_INVENTORY_SQL =
            "delete from inventory where inventory_id = ?";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public InventoryDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Inventory createInventory(Inventory inventory) {
        jdbcTemplate.update(INSERT_INVENTORY_SQL,
                inventory.getProductId(),
                inventory.getQuantity());


        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        inventory.setInventoryId(id);

        return inventory;

    }

    @Override
    public Inventory getInventory(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVENTORY_BY_ID_SQL, this::mapRowToInventory, id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Inventory> getAllInventories() {
        return jdbcTemplate.query(SELECT_ALL_INVENTORIES_SQL, this :: mapRowToInventory);
    }



    @Override
    public void updateInventory(Inventory inventory) {
        jdbcTemplate.update(UPDATE_INVENTORY_SQL,
                inventory.getProductId(),
                inventory.getQuantity(),
                inventory.getInventoryId());


    }

    @Override
    public List<Inventory> getInventoryByProductId(int product_id)
    {
        return jdbcTemplate.query(SELECT_INVENTORY_BY_PRODUCTID_SQL, this ::mapRowToInventory, product_id);
    }

    @Override
    public void deleteInventory(int id) {
        jdbcTemplate.update(DELETE_INVENTORY_SQL, id);

    }

    private Inventory mapRowToInventory(ResultSet rs, int rowNum) throws SQLException {
        Inventory m1 = new Inventory();
        m1.setInventoryId(rs.getInt("inventory_id"));
        m1.setProductId(rs.getInt("product_id"));
        m1.setQuantity(rs.getInt("quantity"));
        return m1;
    }
}
