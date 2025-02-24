package app;

import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        MathOperation addition = new MathOperation() {
            @Override
            public int operate(int a, int b) {
                return a + b;
            }
        };

        int sum = addition.operate(15, 25);
        System.out.println("Sum result: " + sum);

        StringManipulator manipulator = s -> s.toUpperCase();
        String original = "hey y'all!";
        String upperCased = manipulator.manipulate(original);
        System.out.println("Uppercase string: " + upperCased);

        Function<String, Integer> countUppercaseFunction = StringListProcessor::countUppercase;
        String sampleText = "This is a Sample Text!";
        int uppercaseCount = countUppercaseFunction.apply(sampleText);
        System.out.println("Number of capital letters per line '" + sampleText + "': " + uppercaseCount);

        Supplier<Integer> randomSupplier = () -> RandomNumberGenerator.generateRandomNumber(1, 100);
        int randomNum = randomSupplier.get();
        System.out.println("Random number from 1 to 100: " + randomNum);
    }
}