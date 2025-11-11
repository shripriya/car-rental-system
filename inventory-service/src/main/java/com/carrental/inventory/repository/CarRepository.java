package com.carrental.inventory.repository;

import com.carrental.inventory.domain.Car;
import com.carrental.inventory.domain.CarType;

import java.util.*;

public interface CarRepository {
    Car save(Car car);
    Optional<Car> findById(Long id);
    List<Car> findAll();
    void deleteById(Long id);
    Car update(Car car);
    List<Car> findByType(CarType type);
}
