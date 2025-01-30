package app;

public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());

        System.out.println("Weight of the box of apples: " + appleBox.getWeight());

        Box<Orange> orangeBox = new Box<>();
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());

        System.out.println("Weight of the box of oranges: " + orangeBox.getWeight());

        System.out.println("Do the boxes have the same weight? " + appleBox.compare(orangeBox));

        Box<Apple> anotherAppleBox = new Box<>();
        anotherAppleBox.addFruit(new Apple());

        System.out.println("Weight of the second box of apples before merging: " + anotherAppleBox.getWeight());
        appleBox.merge(anotherAppleBox);
        System.out.println("Weight of the first box of apples after merging: " + appleBox.getWeight());
        System.out.println("Weight of the second box after merging: " + anotherAppleBox.getWeight());
    }
}