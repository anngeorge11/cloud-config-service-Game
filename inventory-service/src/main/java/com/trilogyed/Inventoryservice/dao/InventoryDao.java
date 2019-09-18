package com.trilogyed.Inventoryservice.dao;

import com.trilogyed.Inventoryservice.model.Inventory;

import java.util.List;

public interface InventoryDao {

    public Inventory createInventory(Inventory inventory);
    public Inventory getInventory(int id);
    public List<Inventory> getAllInventories();
    public List<Inventory> getInventoryByProductId(int product_id);
    public void updateInventory(Inventory inventory);
    public void deleteInventory(int id);
}
