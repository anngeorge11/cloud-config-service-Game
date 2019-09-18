package com.trilogyed.Productservice.dao;

import com.trilogyed.Productservice.model.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductDaoTests {

    @Autowired
    protected ProductDao dao;

    @Before
    public void setUp() throws Exception {
        // clean out the test db
        List<Product> mList = dao.getAllProducts();

        mList.stream()
                .forEach(product -> dao.deleteProduct(product.getProduct_id()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteProduct() {
        Product product = new Product();
        product.setProduct_name("Knife");
        product.setProduct_description("Cutlery");
        product.setList_price(new BigDecimal(25.45));
        product.setUnit_cost(new BigDecimal(34));

        product = dao.createProduct(product);

       Product product2 = dao.getProduct(product.getProduct_id());
        assertEquals(product.toString(), product2.toString());
        dao.deleteProduct(product.getProduct_id());

        product2 = dao.getProduct(product.getProduct_id());
        assertNull(product2);

    }

    @Test
    public void getAllProducts() {
        Product product = new Product();
        product.setProduct_name("Knife");
        product.setProduct_description("Cutlery");
        product.setList_price(new BigDecimal(25.45));
        product.setUnit_cost(new BigDecimal(34));
        product = dao.createProduct(product);

        List<Product> productList = dao.getAllProducts();

        assertEquals(productList.size(), 1);
    }


    @Test
    public void updateCustomer() {

        Product product = new Product();
        product.setProduct_name("Knife");
        product.setProduct_description("Cutlery");
        product.setList_price(new BigDecimal(25.45));
        product.setUnit_cost(new BigDecimal(34));

        product = dao.createProduct(product);

        product.setProduct_name("Cake Knife");

        dao.updateProduct(product);

        Product product1 = dao.getProduct(product.getProduct_id());
        assertEquals(product1.toString(), product.toString());


    }


}
