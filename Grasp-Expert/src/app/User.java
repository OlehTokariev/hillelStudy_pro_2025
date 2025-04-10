package app;

public class User {
    private final String name;
    private Address address;
    public User(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public Address getAddress() {
        return address;
    }
    @Override
    public String toString() {
        return "User: " + name + ", Address: " + (address != null ? address.toString() : "not added");
    }
}