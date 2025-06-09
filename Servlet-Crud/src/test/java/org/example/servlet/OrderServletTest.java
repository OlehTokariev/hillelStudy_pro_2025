package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Order;
import org.example.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class OrderServletTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private OrderServlet orderServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() throws Exception {
        OrderServlet servlet = new OrderServlet();
        Order order = new Order(1, new Date(), 100.0, List.of());

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        ByteArrayInputStream bais = new ByteArrayInputStream(objectMapper.writeValueAsBytes(order));
        ServletInputStream sis = new ServletInputStream() {
            @Override public int read() { return bais.read(); }
            @Override public boolean isFinished() { return bais.available() == 0; }
            @Override public boolean isReady() { return true; }
            @Override public void setReadListener(ReadListener listener) {}
        };

        when(req.getInputStream()).thenReturn(sis);

        servlet.doPost(req, resp);

        verify(resp).setStatus(HttpServletResponse.SC_CREATED);
    }

    @Test
    void testGetOrder() throws Exception {
        OrderServlet servlet = new OrderServlet();
        Order order = new Order(1, new Date(), 100.0, List.of());
        servlet.doPost(mockRequest(order), mock(HttpServletResponse.class));

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        when(req.getPathInfo()).thenReturn("/1");
        when(resp.getOutputStream()).thenReturn(new ServletOutputStream() {
            @Override public void write(int b) { baos.write(b); }
            @Override public boolean isReady() { return true; }
            @Override public void setWriteListener(WriteListener listener) {}
        });

        servlet.doGet(req, resp);

        verify(resp).setContentType("application/json");
        assert baos.toString().contains("\"id\":1");
    }

    @Test
    void testUpdateOrderSimple() throws Exception {
        Order originalOrder = new Order(1, new Date(), 100.0, List.of(
                new Product(1, "Old", 50.0)
        ));
        orderServlet.orderStorage.put(1, originalOrder);

        Order updatedOrder = new Order(1, new Date(), 200.0, List.of(
                new Product(1, "New", 100.0),
                new Product(2, "Second", 100.0)
        ));

        String jsonBody = objectMapper.writeValueAsString(updatedOrder);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(jsonBody.getBytes());

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override public int read() { return inputStream.read(); }
            @Override public boolean isFinished() { return inputStream.available() == 0; }
            @Override public boolean isReady() { return true; }
            @Override public void setReadListener(ReadListener readListener) {}
        };
        when(req.getInputStream()).thenReturn(servletInputStream);

        ServletOutputStream servletOutputStream = new ServletOutputStream() {
            @Override public void write(int b) {}
            @Override public boolean isReady() { return true; }
            @Override public void setWriteListener(WriteListener writeListener) {}
        };
        when(resp.getOutputStream()).thenReturn(servletOutputStream);

        orderServlet.doPut(req, resp);

        Order result = orderServlet.orderStorage.get(1);
        assertNotNull(result);
        assertEquals(200.0, result.getCost());
        assertEquals(2, result.getProducts().size());

        verify(resp).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void testDeleteOrder() throws Exception {
        OrderServlet servlet = new OrderServlet();
        Order order = new Order(2, new Date(), 200.0, List.of());
        servlet.doPost(mockRequest(order), mock(HttpServletResponse.class));

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getPathInfo()).thenReturn("/2");

        servlet.doDelete(req, resp);

        verify(resp).setStatus(HttpServletResponse.SC_OK);
    }

    private HttpServletRequest mockRequest(Order order) throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        ByteArrayInputStream bais = new ByteArrayInputStream(objectMapper.writeValueAsBytes(order));

        when(req.getInputStream()).thenReturn(new ServletInputStream() {
            @Override public int read() { return bais.read(); }
            @Override public boolean isFinished() { return bais.available() == 0; }
            @Override public boolean isReady() { return true; }
            @Override public void setReadListener(ReadListener listener) {}
        });

        return req;
    }
}