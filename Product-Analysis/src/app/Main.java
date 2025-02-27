package app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Coffee Maker", "Appliances", 80.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Blender", "Appliances", 50.0)
        );

        Map<String, Double> averagePriceByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.averagingDouble(Product::getPrice)));

        System.out.println("Average price of products by category: ");
        averagePriceByCategory.forEach((category, avgPrice) ->
                System.out.println(category + " -> " + avgPrice)
        );

        Optional<Map.Entry<String, Double>> maxCategory = averagePriceByCategory.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        if (maxCategory.isPresent()) {
            System.out.println("Category with the highest average price: " + maxCategory.get().getKey() +
                    " (average price: " + maxCategory.get().getValue() + ")");
        } else {
            System.out.println("Failed to determine the category with the highest average price.");
        }
    }
}