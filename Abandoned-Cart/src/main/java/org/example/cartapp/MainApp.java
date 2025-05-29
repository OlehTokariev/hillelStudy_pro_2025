package org.example.cartapp;

import org.example.cartapp.model.Product;
import org.example.cartapp.repository.ProductRepository;
import org.example.cartapp.service.Cart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductRepository productRepo = context.getBean(ProductRepository.class);
        Cart cart = context.getBean(Cart.class);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1 - Show all products");
            System.out.println("2 - Add product to cart");
            System.out.println("3 - Remove product from cart");
            System.out.println("4 - View cart");
            System.out.println("0 - Exit");

            switch (scanner.nextInt()) {
                case 1:
                    productRepo.getAllProducts().forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Enter product ID to add: ");
                    int addId = scanner.nextInt();
                    Product addProduct = productRepo.findById(addId);
                    if (addProduct != null) {
                        cart.addProduct(addProduct);
                        System.out.println("Added: " + addProduct.getName());
                    } else {
                        System.out.println("Product not found");
                    }
                    break;
                case 3:
                    System.out.print("Enter product ID to remove: ");
                    int removeId = scanner.nextInt();
                    cart.removeProductById(removeId);
                    System.out.println("Item removed from cart.");
                    break;
                case 4:
                    System.out.println("Your current products in cart:");
                    cart.getItems().forEach(System.out::println);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }

        System.out.println("Program terminated.");
    }
}