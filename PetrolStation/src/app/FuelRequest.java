package app;

public class FuelRequest {
    private final int fuelNeeded;

    public FuelRequest(int fuelNeeded) {
        this.fuelNeeded = fuelNeeded;
    }

    public int getFuelNeeded() {
        return fuelNeeded;
    }
}