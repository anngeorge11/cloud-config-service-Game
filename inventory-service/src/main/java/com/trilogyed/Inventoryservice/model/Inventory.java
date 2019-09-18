package com.trilogyed.Inventoryservice.model;

public class Inventory {

    private int inventoryId;
    private int productId;
    private int quantity;

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InventoryServiceLayer{" +
                "inventoryId=" + inventoryId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }


}
