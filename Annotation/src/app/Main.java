package app;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        try {
            Class<?> clazz = ArrayUtils.class;
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(MethodInfo.class) && method.isAnnotationPresent(Author.class)) {
                    MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
                    Author author = method.getAnnotation(Author.class);

                    System.out.println("Method: " + method.getName());
                    System.out.println("Method Info: " + methodInfo.methodName() +
                            ", Return Type: " + methodInfo.returnType() +
                            ", Description: " + methodInfo.description());
                    System.out.println("Author: " + author.firstName() + " " + author.lastName());
                    System.out.println("---------------------------");
                }
            }

            int[] sampleArray = {3, 1, 4, 1, 5, 9};
            System.out.println("Max value: " + ArrayUtils.findMax(sampleArray));
            System.out.println("Min value: " + ArrayUtils.findMin(sampleArray));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}