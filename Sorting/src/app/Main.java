package app;

import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int size = 10;
        int[] array = new int[size];
        Random random = new Random();

        System.out.println("Original array:");
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(array));

        ArrayUtils.mergeSort(array);
        System.out.println("\nSorted array:");
        System.out.println(Arrays.toString(array));

        int target = array[random.nextInt(size)];
        System.out.println("\nSearch the value " + target);

        int index = ArrayUtils.binarySearch(array, target);
        if (index != -1) {
            System.out.println("Number is found " + target + " by index " + index);
        } else {
            System.out.println("Number " + target + " isn't found in the array");
        }
    }
}