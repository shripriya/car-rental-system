package com.carrental.inventory.domain;

public class Car {
    private Long id;
    private CarType type; // SEDAN, SUV, VAN
    private CarStatus status; // Available, Reserved, Maintenance
    private String licensePlate;

    public Car() {}

    public Car(Long id, CarType type, CarStatus status, String licensePlate) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.licensePlate = licensePlate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
