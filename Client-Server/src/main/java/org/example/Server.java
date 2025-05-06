package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    public static final int DEFAULT_PORT = 8080;
    private final int port;
    private final AtomicInteger clientIdGenerator = new AtomicInteger(0);
    private final ConcurrentMap<String, ClientHandler> activeClients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port, using default " + DEFAULT_PORT);
            }
        }
        new Server(port).start();
    }

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.printf("[SERVER] Server started on port %d%n", port);
            while (true) {
                Socket socket = serverSocket.accept();
                String clientName = "client-" + clientIdGenerator.incrementAndGet();
                ClientHandler handler = new ClientHandler(socket, clientName, activeClients);
                activeClients.put(clientName, handler);
                System.out.printf("[SERVER] %s is successfully connected (time: %s)%n",
                        clientName, handler.getConnectionTime());
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}