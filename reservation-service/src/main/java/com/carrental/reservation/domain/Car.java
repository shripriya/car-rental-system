package com.carrental.reservation.domain;

public class Car {
    private Long id;
    private CarType type; // SEDAN, SUV, VAN
    private String licensePlate;
    private Double price;

    public Car() {}

    public Car(Long id, CarType type, String licensePlate, Double price) {
        this.id = id;
        this.type = type;
        this.licensePlate = licensePlate;
        this.price = price;
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

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
