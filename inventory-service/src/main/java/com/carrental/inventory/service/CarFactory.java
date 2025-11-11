package com.carrental.inventory.service;

import com.carrental.inventory.domain.Car;
    import com.carrental.inventory.domain.CarType;

public class CarFactory {
    public static Car createCar(CarType type, String licensePlate) {
        Car car = new Car();
        car.setType(type);
        car.setLicensePlate(licensePlate);
        car.setStatus(com.carrental.inventory.domain.CarStatus.AVAILABLE);
        return car;
    }
}
