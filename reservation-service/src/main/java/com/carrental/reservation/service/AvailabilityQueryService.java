package com.carrental.reservation.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.carrental.reservation.domain.Car;
import com.carrental.reservation.domain.Reservation;
import com.carrental.reservation.repository.ReservationRepository;

@Service
public class AvailabilityQueryService {
    private final ReservationRepository reservationRepository;
    private final RestTemplate restTemplate;
    private final PricingStrategy pricingStrategy;

    @Autowired
    public AvailabilityQueryService(ReservationRepository reservationRepository,
            RestTemplate restTemplate,
            PricingStrategy pricingStrategy) {
        this.reservationRepository = reservationRepository;
        this.restTemplate = restTemplate;
        this.pricingStrategy = pricingStrategy;
    }

    private boolean isCarAvailable(Long carId, LocalDateTime startDate, LocalDateTime endDate) {
        // Placeholder logic for checking vehicle availability
        List<Reservation> existingReservations = reservationRepository.findByCarIdAndDateRange(carId, startDate,
                endDate);
        return existingReservations.isEmpty();
    }

    @SuppressWarnings("unchecked")
    public List<Car> searchAvailableCars(String carType, String startDate, String endDate) {
        // Placeholder logic for searching available cars
        ResponseEntity<List<Car>> response = restTemplate.exchange(
                "http://inventory-service/cars?type=" + carType,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Car>>() {
                });
        List<Car> allCars = response.getBody();

        // check for car availability based on reservations
        List<Car> availableCars = getAvailableCars(allCars, carType, startDate, endDate);
        return availableCars;
    }

    private List<Car> getAvailableCars(List<Car> allCars, String carType, String startDate, String endDate) {
        List<Car> availableCars = new java.util.ArrayList<>();
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        // add pricing strategy here
        int days = (int) java.time.Duration.between(start, end).toDays();
        double price = pricingStrategy.calculatePrice(days, carType);

        for (Car car : allCars) {
            if (isCarAvailable(car.getId(), start, end)) {
                car.setPrice(price);
                availableCars.add(car);
            }
        }
        return availableCars;
    }

}
