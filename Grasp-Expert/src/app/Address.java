package app;

public class Address {
    private final String street;
    private final String city;
    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }
    @Override
    public String toString() {
        return street + ", " + city;
    }
}