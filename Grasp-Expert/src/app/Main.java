package app;

public class Main {
    public static void main(String[] args) {

        User user = new User("Alice");

        Address address = new Address("336 Middlebrook dr.", "Clemmons");

        user.setAddress(address);

        System.out.println("User " + user.getName() + "'s address: " + user.getAddress());
        System.out.println(user);
    }
}