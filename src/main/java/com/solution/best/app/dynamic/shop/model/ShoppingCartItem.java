package com.solution.best.app.dynamic.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShoppingCartItem implements Serializable {

    private int quantiy;
    private Product product;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(int quantiy, Product product) {
        this.quantiy = quantiy;
        this.product = product;
    }

    public int getQuantiy() {
        return quantiy;
    }

    public void setQuantiy(int quantiy) {
        this.quantiy = quantiy;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getTotalPrice() {
        return product.getUnitPrice().multiply(BigDecimal.valueOf(quantiy)).setScale(2, RoundingMode.HALF_UP);
    }
}
