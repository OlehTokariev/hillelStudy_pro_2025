package app;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    private static final String BASE_PATH = "Text-Handler/files/";

    public static void main(String[] args) throws IOException {
        FileHandler handler = new FileHandler();
        String newFileName = "myfile";
        String content = "Super information.";
        String path = BASE_PATH + newFileName + ".txt";

        getOutput(handler.createFile(path));
        getOutput(handler.writeToFile(Paths.get(path), content));
        getOutput("CONTENT: " + handler.readFromFile(path));
    }

    private static void getOutput(String output) {
        System.out.println(output);
    }
}