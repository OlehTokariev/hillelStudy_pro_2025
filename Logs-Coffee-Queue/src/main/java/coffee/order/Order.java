package coffee.order;

import java.util.Objects;

public class Order {
    private final int orderNumber;
    private final String customerName;

    public Order(int orderNumber, String customerName) {
        this.orderNumber   = orderNumber;
        this.customerName = Objects.requireNonNull(customerName, "Customer name can't be null");
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return String.format("Order{#=%d, customer='%s'}", orderNumber, customerName);
    }
}