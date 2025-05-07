package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) {
        String host = DEFAULT_HOST;
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            host = args[0];
        }
        if (args.length > 1) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port, using default " + DEFAULT_PORT);
            }
        }

        try (Socket socket = new Socket(host, port);
             BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out   = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner   = new Scanner(System.in)) {

            System.out.printf("Connected to %s:%d (type 'exit' to disconnect)%n", host, port);

            new Thread(() -> {
                try {
                    String resp;
                    while ((resp = in.readLine()) != null) {
                        System.out.println("[SERVER]: " + resp);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            while (true) {
                String msg = scanner.nextLine();
                out.println(msg);
                if ("exit".equalsIgnoreCase(msg.trim())) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}