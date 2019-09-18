package com.trilogyed.Productservice.model;

import java.math.BigDecimal;
import java.math.MathContext;

public class Product {

    private int product_id;
    private String product_name;
    private String product_description;
    private BigDecimal list_price;
    private BigDecimal unit_cost;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public BigDecimal getList_price() {
        return list_price;
    }

    public void setList_price(BigDecimal list_price) {
        this.list_price = list_price.abs(new MathContext(2));
    }

    public BigDecimal getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(BigDecimal unit_cost) {
        this.unit_cost = unit_cost.abs(new MathContext(2));
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_description='" + product_description + '\'' +
                ", list_price=" + list_price +
                ", unit_cost=" + unit_cost +
                '}';
    }
}
