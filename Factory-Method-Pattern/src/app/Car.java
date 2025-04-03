package app;

public class Car implements Transport {
    @Override
    public void move() {
        System.out.println("Car is moving from Dnipro to Lviv.");
    }
}