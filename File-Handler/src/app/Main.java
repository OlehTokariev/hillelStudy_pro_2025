package app;

import java.io.IOException;

public class Main {

    private final static String BASE_PATH = "File-Handler/files/";

    public static void main(String[] args) throws IOException {
        app.FileHandler handler = new app.FileHandler();

        String fileName = "myfile";
        String fileContent = "My very important information.";
        String result = handler.writeFile(fileName, fileContent);
        String content = handler.readFile(BASE_PATH + fileName + ".txt");

        getOutput("RESULT: " + result);
        getOutput("FILE CONTENT: " + content);
    }

    private static void getOutput(String output) {
        System.out.println(output);
    }
}