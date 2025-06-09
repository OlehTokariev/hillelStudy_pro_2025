package org.example.repository;

import org.example.model.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    private final Map<Long, Order> orders = new HashMap<>();
    private long nextId = 1;

    public Order getById(Long id) {
        return orders.get(id);
    }

    public List<Order> getAll() {
        return new ArrayList<>(orders.values());
    }

    public Order add(Order order) {
        order.setId(nextId++);
        orders.put(order.getId(), order);
        return order;
    }
}