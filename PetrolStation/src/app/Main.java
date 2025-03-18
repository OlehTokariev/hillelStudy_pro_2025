package app;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PetrolStation station = new PetrolStation(100);

        station.doRefuel(10);
        station.doRefuel(20);
        station.doRefuel(15);
        station.doRefuel(30);
        station.doRefuel(25);

        Thread.sleep(20000);

        station.shutdown();
    }
}