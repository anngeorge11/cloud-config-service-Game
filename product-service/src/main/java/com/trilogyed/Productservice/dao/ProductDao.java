package com.trilogyed.Productservice.dao;

import com.trilogyed.Productservice.model.Product;

import java.util.List;

public interface ProductDao {

    public Product createProduct(Product product);
    public Product getProduct(int id);
    public List<Product> getAllProducts();
    // public List<Customer> getCustomersByCategory(String category);
    public void updateProduct(Product product);
    public void deleteProduct(int id);
}
