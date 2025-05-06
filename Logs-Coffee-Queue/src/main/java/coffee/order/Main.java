package coffee.order;

public class Main {
    public static void main(String[] args) {
        CoffeeOrderBoard board = new CoffeeOrderBoard();

        board.add("Oleh");
        board.add("Julia");
        board.add("Kostya");
        board.add("Nick");
        board.add("Anna");
        board.draw();

        board.deliver(4);
        board.deliver();
        board.draw();
    }
}