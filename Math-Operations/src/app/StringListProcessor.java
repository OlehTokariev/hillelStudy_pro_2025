package app;

public class StringListProcessor {
    public static int countUppercase(String s) {
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                count++;
            }
        }
        return count;
    }
}