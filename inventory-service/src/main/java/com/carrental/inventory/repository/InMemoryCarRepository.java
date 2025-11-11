package com.carrental.inventory.repository;

import com.carrental.inventory.domain.Car;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCarRepository implements CarRepository {
    private final Map<Long, Car> cars = new ConcurrentHashMap<>();
    private long nextId = 1;

    @Override
    public Car save(Car car) {
        car.setId(nextId++);
        cars.put(car.getId(), car);
        return car;
    }

    @Override
    public Optional<Car> findById(Long id) {
        return Optional.ofNullable(cars.get(id));
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(cars.values());
    }

    @Override
    public void deleteById(Long id) {
        cars.remove(id);
    }

    @Override
    public Car update(Car car) {
        cars.put(car.getId(), car);
        return car;
    }

    @Override
    public List<Car> findByType(com.carrental.inventory.domain.CarType type) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars.values()) {
            if (car.getType() == type) {
                result.add(car);
            }
        }
        return result;
    }
}
