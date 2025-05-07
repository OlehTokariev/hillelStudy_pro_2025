package org.example;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentMap;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final String name;
    private final String connectionTime;
    private final BufferedReader in;
    private final PrintWriter out;
    private final ConcurrentMap<String, ClientHandler> activeClients;

    public ClientHandler(Socket socket, String name, ConcurrentMap<String, ClientHandler> activeClients)
            throws IOException {
        this.socket = socket;
        this.name = name;
        this.activeClients = activeClients;
        this.connectionTime = LocalDateTime.now()
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public String getConnectionTime() {
        return connectionTime;
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                if ("exit".equalsIgnoreCase(line.trim())) {
                    out.println("bye");
                    break;
                }
                out.println("Echo: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            cleanup();
        }
    }

    private void cleanup() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException ignored) {}
        activeClients.remove(name);
        System.out.printf("[SERVER] %s is disconnected%n", name);
    }
}