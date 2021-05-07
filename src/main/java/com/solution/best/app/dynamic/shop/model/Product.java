package com.solution.best.app.dynamic.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable{
    private int id;
    private String name;
    private BigDecimal unitPrice;

    public Product() {
    }

    public Product(int id, String name, BigDecimal unitPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    
}
