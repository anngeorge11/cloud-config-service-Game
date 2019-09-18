package com.trilogyed.Inventoryservice.service;

import com.trilogyed.Inventoryservice.dao.InventoryDao;
import com.trilogyed.Inventoryservice.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class InventoryServiceLayer {

    InventoryDao dao;


    @Autowired
    public InventoryServiceLayer(InventoryDao dao) {

        this.dao = dao;
    }

    public Inventory createInventory(Inventory inventory) {
        dao.createInventory(inventory);
        return inventory;
    }

    public Inventory getInventory(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("Inventory id should be greater than 0");

        }
        return dao.getInventory(id);
    }

    public List<Inventory> getAllInventories() {

        return dao.getAllInventories();
    }

    public Inventory updateInventory(int id, Inventory inventory) {
        List<Inventory> proList = dao.getAllInventories();
        for (Inventory c : proList) {
            if (c.getInventoryId() == id) {
                inventory.setInventoryId(id);

                dao.updateInventory(inventory);

                if (id != inventory.getInventoryId()) {
                    throw new IllegalArgumentException("Inventory ID on path must match the ID in the Inventory object");

                }
            }
        }
        return dao.getInventory(id);

    }

    public void deleteInventory (int id) {

        if (id != dao.getInventory(id).getInventoryId()) {
            throw new IllegalArgumentException("Inventory ID on path must match the ID in the Inventory object.");
        }

        dao.deleteInventory(id);
    }

    public List<Inventory> getInventoryByProductId(int product_id) {

        if (dao.getInventoryByProductId(product_id).isEmpty()){
            throw new NoSuchElementException("There is no inventory in the list that matches the product_id sent");
        }
        return dao.getInventoryByProductId(product_id);
    }


}
