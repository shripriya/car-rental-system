package com.carrental.reservation.service;

public class DefaultPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(int days, String carType) {
        double baseRate;
        switch (carType) {
            case "SUV": baseRate = 70; break;
            case "Van": baseRate = 60; break;
            default: baseRate = 50; break;
        }
        return baseRate * days;
    }
}
