package coffee.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class CoffeeOrderBoard {
    private static final Logger logger = LoggerFactory.getLogger(CoffeeOrderBoard.class);

    private final List<Order> orders = new LinkedList<>();
    private int nextOrderNumber = 1;

    public synchronized Order add(String customerName) {
        Order order = new Order(nextOrderNumber++, customerName);
        if (customerName == null || customerName.isBlank()) {
            logger.warn("Name isn't filled in the {}", order);
            throw new IllegalArgumentException("Can't add order: customer name is null or blank");
        }

        orders.add(order);
        logger.info("New order added: {}", order);
        return order;
    }

    public synchronized Order deliver() {
        if (orders.isEmpty()) {
            NoSuchElementException ex = new NoSuchElementException("Can't deliver next order: queue is empty");
            logger.error("Deliver failed: {}", ex.getMessage(), ex);
            throw ex;
        }
        Order o = orders.remove(0);
        return deliverInternal(o);
    }

    public synchronized Order deliver(int orderNumber) {
        Iterator<Order> it = orders.iterator();
        while (it.hasNext()) {
            Order o = it.next();
            if (o.getOrderNumber() == orderNumber) {
                it.remove();
                return deliverInternal(o);
            }
        }
        NoSuchElementException ex = new NoSuchElementException(
                "Can't deliver order #" + orderNumber + ": not found"
        );
        logger.error("{}", ex.getMessage(), ex);
        throw ex;
    }

    private Order deliverInternal(Order order) {
        System.out.println("Starting to deliver: " + order);
        logger.info("Order delivered to: {}", order);
        return order;
    }

    public synchronized void draw() {
        logger.info("Coffee order board:");
        System.out.println("Num | Name");
        for (Order o : orders) {
            System.out.printf("%d | %s%n", o.getOrderNumber(), o.getCustomerName());
        }
    }
}