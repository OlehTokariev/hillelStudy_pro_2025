package app;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class PetrolStation {
    private int amount;

    private final BlockingQueue<FuelRequest> queue;

    private final ExecutorService executor;

    public PetrolStation(int amount) {
        this.amount = amount;
        this.queue = new LinkedBlockingQueue<>();
        this.executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executor.submit(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        FuelRequest request = queue.take();
                        processRequest(request);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
    }

    public void doRefuel(int fuelNeeded) {
        FuelRequest request = new FuelRequest(fuelNeeded);
        try {
            queue.put(request);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private synchronized void processRequest(FuelRequest request) {
        int waitTime = ThreadLocalRandom.current().nextInt(3, 11);
        try {
            System.out.println("Processing refuel of " + request.getFuelNeeded() + " fuel. Wait time: " + waitTime + " seconds.");
            Thread.sleep(waitTime * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (this.amount >= request.getFuelNeeded()) {
            this.amount -= request.getFuelNeeded();
            System.out.println("Refueled " + request.getFuelNeeded() + " fuel. Remaining fuel: " + this.amount);
        } else {
            System.out.println("Not enough fuel for refuel: required " + request.getFuelNeeded() + ", available " + this.amount);
        }
    }

    public void shutdown() {
        executor.shutdownNow();
    }
}