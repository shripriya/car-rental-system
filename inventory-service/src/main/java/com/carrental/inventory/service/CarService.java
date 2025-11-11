package com.carrental.inventory.service;

import com.carrental.inventory.domain.Car;
import com.carrental.inventory.domain.CarType;
import com.carrental.inventory.repository.CarRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public Car addCar(CarType type, String licensePlate) {
        Car car = CarFactory.createCar(type, licensePlate);
        return carRepository.save(car);
    }
    public Car updateCar(Long id, Car car) {
        car.setId(id);
        return carRepository.update(car);
    }
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
    public boolean checkAvailability(CarType type) {
        return carRepository.findAll().stream()
            .anyMatch(car -> car.getType() == type && car.getStatus() == com.carrental.inventory.domain.CarStatus.AVAILABLE);
    }
    public List<Car> getCarByType(String carType) {
        CarType type = CarType.valueOf(carType.toUpperCase());
        return carRepository.findByType(type);
    }
}
