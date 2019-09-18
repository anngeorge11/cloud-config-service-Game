package com.trilogyed.Productservice.service;

import com.trilogyed.Productservice.dao.ProductDao;
import com.trilogyed.Productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceLayer {

    ProductDao dao;


    @Autowired
    public ProductServiceLayer(ProductDao dao) {
        this.dao = dao;
    }

    public Product createProduct(Product product) {
        dao.createProduct(product);
        return product;
    }

    public Product getProduct(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("Product id should be greater than 0");

        }
        return dao.getProduct(id);
    }

    public List<Product> getAllProducts() {

        return dao.getAllProducts();
    }

    public Product updateProduct(int id, Product product) {
        List<Product> proList = dao.getAllProducts();
        for (Product c : proList) {
            if (c.getProduct_id() == id) {
                product.setProduct_id(id);

                dao.updateProduct(product);

                if (id != product.getProduct_id()) {
                    throw new IllegalArgumentException("Product ID on path must match the ID in the Product object");

                }
            }
        }
        return dao.getProduct(id);

    }

    public void deleteProduct (int id) {

        if (id != dao.getProduct(id).getProduct_id()) {
            throw new IllegalArgumentException("Product ID on path must match the ID in the Product object.");
        }

        dao.deleteProduct(id);
    }
}
