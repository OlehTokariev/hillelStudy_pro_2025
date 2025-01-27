package app;

public class Main {

    public static void main(String[] args) {
        Corrector corrector = new Corrector();
        DataProvider dataProvider = new DataProvider();
        String output = corrector.handleData(dataProvider.getData());
        getOutput(output);
    }

    private static void getOutput(String output) {
        System.out.println(output);
    }
}