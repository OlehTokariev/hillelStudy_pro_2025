package org.example.dao;

import org.example.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    void addCustomer(Customer customer);
    Optional<Customer> getCustomerById(Long id);
    List<Customer> getAllCustomers();
    void updateCustomer(Customer customer);
    void deleteCustomer(Long id);
}