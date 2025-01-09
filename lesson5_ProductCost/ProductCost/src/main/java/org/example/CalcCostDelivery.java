package org.example;

public class CalcCostDelivery extends CalcCostBase {
    private final static double DELIVERY_PRICE = 7.0;
    @Override
    public double calcCost(Product product) {
        return super.calcCost(product) + DELIVERY_PRICE;
    }
}