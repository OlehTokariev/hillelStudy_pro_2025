package app;

import java.util.concurrent.atomic.AtomicInteger;

public class DataHandler {

    private final String[] fruits = new DataRepository().getData();

    public synchronized void getOutput() {
        StringBuilder sb = new StringBuilder();
        AtomicInteger count = new AtomicInteger(1);

        for (String fruit : fruits) {
            sb.append(String.format("(%d) %s ", count.getAndIncrement(), fruit));
        }

        System.out.println(Thread.currentThread().getName() + ": " + sb);
    }
}