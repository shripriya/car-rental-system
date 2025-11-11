package com.carrental.inventory.repository;

import com.carrental.inventory.domain.Car;
import java.util.*;

public interface CarRepository {
    Car save(Car car);
    Optional<Car> findById(Long id);
    List<Car> findAll();
    void deleteById(Long id);
    Car update(Car car);
}
