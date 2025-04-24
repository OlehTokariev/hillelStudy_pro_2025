package app;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS employees (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "age INTEGER, " +
                "position VARCHAR(100), " +
                "salary FLOAT)";
        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (name, age, position, salary) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getName());
            stmt.setInt(2, employee.getAge());
            stmt.setString(3, employee.getPosition());
            stmt.setFloat(4, employee.getSalary());
            stmt.executeUpdate();
        }
    }

    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employees SET name=?, age=?, position=?, salary=? WHERE id=?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getName());
            stmt.setInt(2, employee.getAge());
            stmt.setString(3, employee.getPosition());
            stmt.setFloat(4, employee.getSalary());
            stmt.setInt(5, employee.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("position"),
                        rs.getFloat("salary")
                ));
            }
        }
        return employees;
    }
}