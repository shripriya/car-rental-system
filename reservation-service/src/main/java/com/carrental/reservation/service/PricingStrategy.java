package com.carrental.reservation.service;

public interface PricingStrategy {
    double calculatePrice(int days, String carType);
}
