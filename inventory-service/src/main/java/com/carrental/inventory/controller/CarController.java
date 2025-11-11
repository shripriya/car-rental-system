package com.carrental.inventory.controller;

import com.carrental.inventory.domain.Car;
import com.carrental.inventory.domain.CarType;
import com.carrental.inventory.service.CarService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @GetMapping("/{type}")
    public List<Car> getCarById(@PathVariable String carType) {
        return carService.getCarByType(carType);
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.addCar(car.getType(), car.getLicensePlate());
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @GetMapping("/availability")
    public boolean checkAvailability(@RequestParam CarType type) {
        return carService.checkAvailability(type);
    }
}
