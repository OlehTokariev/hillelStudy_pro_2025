package app;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();

        try {
            dao.createTable();
            List<Employee> employees = dao.getAllEmployees();
            System.out.println("Employees:");
            employees.forEach(System.out::println);

            dao.addEmployee(new Employee("Nick", 25, "Developer", 3500.0f));
            dao.addEmployee(new Employee("Sarah", 28, "Manager", 1500.0f));

            Employee updated = new Employee(7,
                    "Bob Takkovick", 26,
                    "Senior Developer",
                    5000.0f);
            dao.updateEmployee(updated);
            System.out.println("\nUpdated employee with ID: " + updated.getId());

            employees = dao.getAllEmployees();
            System.out.println("Employees after updating:");
            employees.forEach(System.out::println);

            int deletedId = 10;
            System.out.println("\nDeleting employee with ID: " + deletedId);
            dao.deleteEmployee(deletedId);

            System.out.println("\nFinal list:");
            dao.getAllEmployees().forEach(System.out::println);

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}