package app;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(new User(1, "John Jons", "john@ufc.com"));
        userRepository.addUser(new User(2, "Alex Pereira", "alex@ufc.com"));
        userRepository.addUser(new User(3, "Justin Gaethje", "justin@ufc.com"));

        int searchId = 2;
        userRepository.findUserById(searchId).ifPresentOrElse(
                user -> System.out.println("User with id: " + searchId + " is found: " + user),
                () -> System.out.println("User with id " + searchId + " isn't found")
        );

        String searchEmail = "justin@ufc.com";
        userRepository.findUserByEmail(searchEmail).ifPresentOrElse(
                user -> System.out.println("User is found by email " + searchEmail + ": " + user),
                () -> System.out.println("User with email " + searchEmail + " isn't found")
        );

        userRepository.findAllUsers().ifPresentOrElse(
                users -> {
                    System.out.println("Total users: " + users.size());
                    System.out.println("Users list: ");
                    users.forEach(System.out::println);
                },
                () -> System.out.println("The list of users is empty")
        );
    }
}