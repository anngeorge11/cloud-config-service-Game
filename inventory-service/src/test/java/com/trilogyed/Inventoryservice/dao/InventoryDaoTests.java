package com.trilogyed.Inventoryservice.dao;

import com.trilogyed.Inventoryservice.model.Inventory;
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
public class InventoryDaoTests {
    @Autowired
    protected InventoryDao dao;

    @Before
    public void setUp() throws Exception {
        // clean out the test db
        List<Inventory> mList = dao.getAllInventories();

        mList.stream()
                .forEach(inventory -> dao.deleteInventory(inventory.getInventoryId()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(2);
        inventory.setQuantity(12);

        inventory = dao.createInventory(inventory);

        Inventory inventory2 = dao.getInventory(inventory.getInventoryId());
        assertEquals(inventory.toString(), inventory2.toString());
        dao.deleteInventory(inventory.getInventoryId());

        inventory2 = dao.getInventory(inventory.getInventoryId());
        assertNull(inventory2);

    }


    @Test
    public void getAllInventories() {
        Inventory inventory = new Inventory();
        inventory.setProductId(2);
        inventory.setQuantity(12);

        inventory = dao.createInventory(inventory);


        List<Inventory> inventoryList = dao.getAllInventories();

        assertEquals(inventoryList.size(), 1);
    }


    @Test
    public void updateInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(2);
        inventory.setQuantity(12);

        inventory = dao.createInventory(inventory);
        inventory.setQuantity(10);

        dao.updateInventory(inventory);
        Inventory inventory1 = dao.getInventory(inventory.getInventoryId());

        assertEquals(inventory1.toString(), inventory.toString());

    }

    @Test
    public void getInventoryByProductId() {

        Inventory inventory = new Inventory();
        inventory.setProductId(2);
        inventory.setQuantity(12);

        inventory = dao.createInventory(inventory);

        Inventory inventory1 = new Inventory();
        inventory1.setProductId(2);
        inventory1.setQuantity(12);

        inventory1 = dao.createInventory(inventory1);


        List<Inventory> tList = dao.getInventoryByProductId(2);
        assertEquals(tList.size(), 2);



    }


}
