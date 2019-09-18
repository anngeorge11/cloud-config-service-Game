package com.trilogyed.Inventoryservice.controller;

import com.trilogyed.Inventoryservice.model.Inventory;
import com.trilogyed.Inventoryservice.service.InventoryServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@CrossOrigin
@RefreshScope
public class InventoryController {

    @Autowired
    InventoryServiceLayer service;



    public InventoryController(InventoryServiceLayer service)
    {

        this.service = service;
    }


    @RequestMapping(value = "/inventory", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory createInventory(@RequestBody @Valid Inventory inventory) {

        return service.createInventory(inventory);
    }

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Inventory getInventory(@PathVariable @Valid int id) {

        return service.getInventory(id);
    }

    @RequestMapping(value = "/inventories", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Inventory> getAllInventories() {
        return service.getAllInventories();

    }

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Inventory updateInventory(@PathVariable("id") int id, @RequestBody @Valid Inventory inventory) {

        return service.updateInventory(id,inventory);
    }

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.DELETE)
    public void deleteInventory(@PathVariable @Valid int id) {

        service.deleteInventory(id);
    }

    @RequestMapping(value = "/inventory/productId/{productId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Inventory> getInventoryByProductId(@PathVariable @Valid int productId) {

        if (service.getInventoryByProductId(productId).isEmpty()){
            throw new NoSuchElementException("There is no matching productId");
        }
        return service.getInventoryByProductId(productId);
    }

}
