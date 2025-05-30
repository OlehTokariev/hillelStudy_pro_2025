package org.example.dao;

import org.example.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private final JdbcTemplate jdbcTemplate;

    public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Customer> customerRowMapper = (rs, rowNum) -> new Customer(
            rs.getLong("id"),
            rs.getString("full_name"),
            rs.getString("email"),
            rs.getString("social_security_number")
    );

    @Override
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customer (full_name, email, social_security_number) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, customer.getFullName(), customer.getEmail(), customer.getSocialSecurityNumber());
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return jdbcTemplate.query(sql, customerRowMapper, id).stream().findFirst();
    }

    @Override
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, customerRowMapper);
    }

    @Override
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET full_name = ?, email = ?, social_security_number = ? WHERE id = ?";
        jdbcTemplate.update(sql, customer.getFullName(), customer.getEmail(), customer.getSocialSecurityNumber(), customer.getId());
    }

    @Override
    public void deleteCustomer(Long id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}