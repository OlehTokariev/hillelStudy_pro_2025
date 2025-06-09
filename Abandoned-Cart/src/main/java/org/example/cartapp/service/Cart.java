package org.example.cartapp.service;

import org.example.cartapp.model.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private final List<Product> items = new ArrayList<>();

    public void addProduct(Product product) {
        items.add(product);
    }

    public void removeProductById(int id) {
        items.removeIf(p -> p.getId() == id);
    }

    public List<Product> getItems() {
        return items;
    }
}