package org.example.cartapp.repository;

import org.example.cartapp.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        products.add(new Product(1, "Laptop", 1200.0));
        products.add(new Product(2, "Smartphone", 800.0));
        products.add(new Product(3, "Headphones", 150.0));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product findById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}