package com.carrental.inventory.service;

import com.carrental.inventory.domain.Car;
import com.carrental.inventory.domain.CarStatus;
import com.carrental.inventory.domain.CarType;
import com.carrental.inventory.repository.InMemoryCarRepository;
import org.junit.jupiter.api.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {
    private CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarService(new InMemoryCarRepository());
    }

    @Test
    void testAddAndGetCar() {
        Car car = carService.addCar(CarType.SEDAN, "ABC123");
        assertNotNull(car.getId());
        Car found = carService.getCarById(car.getId());
        assertEquals(CarType.SEDAN, found.getType());
        assertEquals(CarStatus.AVAILABLE, found.getStatus());
    }

    @Test
    void testUpdateCar() {
        Car car = carService.addCar(CarType.SUV, "XYZ789");
        car.setStatus(CarStatus.RESERVED);
        Car updated = carService.updateCar(car.getId(), car);
        assertEquals(CarStatus.RESERVED, updated.getStatus());
    }

    @Test
    void testDeleteCar() {
        Car car = carService.addCar(CarType.VAN, "VAN001");
        carService.deleteCar(car.getId());
        assertNull(carService.getCarById(car.getId()));
    }

    @Test
    void testCheckAvailability() {
        carService.addCar(CarType.SEDAN, "SED001");
        assertTrue(carService.checkAvailability(CarType.SEDAN));
        assertFalse(carService.checkAvailability(null));
    }
}
