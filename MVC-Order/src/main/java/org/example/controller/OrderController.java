package org.example.controller;

import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.getById(id);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.getAll();
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        return orderRepository.add(order);
    }
}