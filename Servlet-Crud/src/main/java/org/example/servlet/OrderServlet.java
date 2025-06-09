package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Order;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/orders/*")
public class OrderServlet extends HttpServlet {
    Map<Integer, Order> orderStorage = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Order order = objectMapper.readValue(req.getInputStream(), Order.class);
        orderStorage.put(order.getId(), order);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();
        if (path == null || path.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int id = Integer.parseInt(path.substring(1));
        Order order = orderStorage.get(id);

        if (order != null) {
            resp.setContentType("application/json");
            objectMapper.writeValue(resp.getOutputStream(), order);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Order updatedOrder = objectMapper.readValue(req.getInputStream(), Order.class);
        orderStorage.put(updatedOrder.getId(), updatedOrder);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();
        if (path == null || path.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int id = Integer.parseInt(path.substring(1));
        if (orderStorage.remove(id) != null) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}