package org.example;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public void addFruits(T[] fruitsArray) {
        for (T fruit : fruitsArray) {
            fruits.add(fruit);
        }
    }

    public float getWeight() {
        if (fruits.isEmpty()) {
            return 0;
        }
        return fruits.size() * fruits.get(0).getWeight();
    }

    public boolean compare(Box<?> anotherBox) {
        return this.getWeight() == anotherBox.getWeight();
    }

    public void merge(Box<T> anotherBox) {
        if (anotherBox == this) {
            return;
        }
        this.fruits.addAll(anotherBox.fruits);
        anotherBox.fruits.clear();
    }

    public int getSize() {
        return fruits.size();
    }
}