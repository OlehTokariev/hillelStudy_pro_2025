package org.example;

import org.example.config.DataSourceConfig;
import org.example.dao.CustomerDao;
import org.example.dao.CustomerDaoImpl;
import org.example.model.Customer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(DataSourceConfig.class, CustomerDaoImpl.class);
        CustomerDao customerDao = context.getBean(CustomerDao.class);

        Customer customer = new Customer(null, "Mark Hobbard", "mark@gmail.com", "336-57-7452");
        customerDao.addCustomer(new Customer(null, "Andy Anderson", "andy.anderson@lifewithlouise.com", "754-21-2354"));
        customerDao.addCustomer(new Customer(null, "Nikki Ash", "hrmanagement@carolinapoolmanagement.nc", "437-09-1200"));
        customerDao.addCustomer(customer);

        customerDao.getAllCustomers().forEach(c ->
                System.out.println(c.getId() + ": " + c.getFullName() + ", " + c.getEmail())
        );
    }
}