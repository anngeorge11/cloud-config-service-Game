package com.trilogyed.Productservice.controller;

import com.trilogyed.Productservice.model.Product;
import com.trilogyed.Productservice.service.ProductServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RefreshScope
public class ProductController {


    @Autowired
    ProductServiceLayer service;



    public ProductController(ProductServiceLayer service)
    {
        this.service = service;
    }


    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody @Valid Product product) {

        return service.createProduct(product);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable @Valid int id) {

        return service.getProduct(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return service.getAllProducts();

    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@PathVariable("id") int id, @RequestBody @Valid Product product) {

        return service.updateProduct(id,product);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable @Valid int id) {

        service.deleteProduct(id);
    }


}
