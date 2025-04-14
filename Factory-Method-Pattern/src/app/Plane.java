package app;

public class Plane implements Transport {
    @Override
    public void move() {
        System.out.println("Plane is flying from Dnipro to Viena.");
    }
}