package org.example.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Long id;
    private LocalDate creationDate;
    private double totalCost;
    private List<Product> products;

    public Order() {}
    public Order(Long id, LocalDate creationDate, double totalCost, List<Product> products) {
        this.id = id;
        this.creationDate = creationDate;
        this.totalCost = totalCost;
        this.products = products;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
}