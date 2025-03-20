package app;

public class ArrayUtils {

    @MethodInfo(
            methodName = "findMax",
            returnType = "int",
            description = "Finds the maximum value in an integer array"
    )
    @Author(
            firstName = "Mark",
            lastName = "Dark"
    )
    public static int findMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    @MethodInfo(
            methodName = "findMin",
            returnType = "int",
            description = "Finds the minimum value in an integer array"
    )
    @Author(
            firstName = "Malfurion",
            lastName = "Stormrage"
    )
    public static int findMin(int[] array) {
        int min = array[0];
        for (int num : array) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }
}