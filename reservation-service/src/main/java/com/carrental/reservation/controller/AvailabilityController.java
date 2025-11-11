package com.carrental.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.reservation.domain.Car;
import com.carrental.reservation.service.AvailabilityQueryService;

@RestController
@RequestMapping("/reservations")
public class AvailabilityController {
    @Autowired
    private AvailabilityQueryService availabilityQueryService;
    
    @GetMapping("/search")
    public ResponseEntity<List<Car>> searchAvailableCars(@RequestParam("carType") String carType,
                                                         @RequestParam("startDate") String startDate,
                                                         @RequestParam("endDate") String endDate) {
        // Placeholder logic for searching available cars
        List<Car> availableCars = availabilityQueryService.searchAvailableCars(carType, startDate, endDate);
        return ResponseEntity.ok().body(availableCars);
    }
    
}
