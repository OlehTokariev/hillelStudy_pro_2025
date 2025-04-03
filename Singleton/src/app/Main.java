package app;


public class Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        logger1.log("First log message");

        Logger logger2 = Logger.getInstance();
        logger2.log("Second log message");

        if (logger1 == logger2) {
            System.out.println("Both getInstance() calls returned the same instance Logger.");
        } else {
            System.out.println("Error: Logger instances are different.");
        }

        logger1.printLogs();
    }
}