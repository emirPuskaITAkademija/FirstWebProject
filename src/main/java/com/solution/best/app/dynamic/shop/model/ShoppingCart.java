package com.solution.best.app.dynamic.shop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart implements Serializable {

    private final List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

    /**
     * Dodaje jednu stavku u korpu
     * <p>
     * @param product
     * @param quantity
     */
    public void addItem(Product product, int quantity) {
        for (ShoppingCartItem item : shoppingCartItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantiy(item.getQuantiy() + quantity);
                return;
            }
        }
        ShoppingCartItem item = new ShoppingCartItem(quantity, product);
        shoppingCartItems.add(item);
    }

    public void removeItem(Product product, int quantity) {
        Iterator<ShoppingCartItem> itemIterator = shoppingCartItems.iterator();
        while (itemIterator.hasNext()) {
            ShoppingCartItem item = itemIterator.next();
            if (item.getProduct().getId() == product.getId()) {
                if (item.getQuantiy() > quantity) {
                    item.setQuantiy(item.getQuantiy() - quantity);
                } else {
                    itemIterator.remove();
                }
            }
        }
    }

    public boolean containsShoppingCartItems() {
        return !shoppingCartItems.isEmpty();
    }

    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

}
