package app;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public void run() {
        TransportFactory carFactory = new CarFactory();
        Transport car = carFactory.createTransport();
        car.move();

        TransportFactory planeFactory = new PlaneFactory();
        Transport plane = planeFactory.createTransport();
        plane.move();
    }
}