package app;


import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static Logger instance;


    private List<String> logs;


    private Logger() {
        logs = new ArrayList<>();
    }


    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }


    public void log(String message) {
        logs.add(message);
        System.out.println("Log: " + message);
    }

    public void printLogs() {
        System.out.println("All logs:");
        for (String log : logs) {
            System.out.println(log);
        }
    }
}