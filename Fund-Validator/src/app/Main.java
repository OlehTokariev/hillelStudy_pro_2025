package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double balance = 1000.00;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Balance is USD " + balance);
        System.out.print("Enter purchase amount, USD: ");
        double purchaseAmount = scanner.nextDouble();

        try {
            processPurchase(balance, purchaseAmount);
            balance -= purchaseAmount;
            System.out.println("Funds are OK. Purchase paid.");
            System.out.println("Balance is USD " + balance);
        } catch (FundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void processPurchase(double balance, double purchaseAmount) throws FundsException {
        if (purchaseAmount > balance) {
            throw new FundsException("Insufficient funds!");
        }
    }
}