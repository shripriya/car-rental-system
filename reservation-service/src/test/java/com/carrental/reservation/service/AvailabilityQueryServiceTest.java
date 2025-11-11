package com.carrental.reservation.service;

import com.carrental.reservation.domain.Car;
import com.carrental.reservation.domain.Reservation;
import com.carrental.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class AvailabilityQueryServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private PricingStrategy pricingStrategy;

    private RestTemplate restTemplate;
    private MockRestServiceServer mockServer;

    @InjectMocks
    private AvailabilityQueryService availabilityQueryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);

        availabilityQueryService = new AvailabilityQueryService(reservationRepository, restTemplate, pricingStrategy);
    }

    @Test
    void testSearchAvailableCars_withOneCarUnavailable() {
        String startDate = "2025-11-15T10:00:00";
        String endDate = "2025-11-18T10:00:00";

        // Mock inventory service response (JSON array of cars)
        String carsJson = "[{\"id\":1,\"type\":\"SUV\"},{\"id\":2,\"type\":\"SUV\"}]";
        mockServer.expect(requestTo("http://inventory-service/cars?type=SUV"))
                .andRespond(withSuccess(carsJson, MediaType.APPLICATION_JSON));

        // Mock reservation repository: car1 has a reservation, car2 is free
        when(reservationRepository.findByCarIdAndDateRange(eq(1L), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList(new Reservation()));
        when(reservationRepository.findByCarIdAndDateRange(eq(2L), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Collections.emptyList());

        // Mock pricing strategy
        when(pricingStrategy.calculatePrice(3, "SUV")).thenReturn(300.0);

        // Execute
        List<Car> availableCars = availabilityQueryService.searchAvailableCars("SUV", startDate, endDate);

        // Verify
        mockServer.verify();
        assertEquals(1, availableCars.size(), "Only one car should be available");
        assertEquals(2L, availableCars.get(0).getId(), "Car2 should be available");
        assertEquals(300.0, availableCars.get(0).getPrice(), "Price should be set by pricing strategy");
    }

    @Test
    void testSearchAvailableCars_allCarsAvailable() {
        String startDate = "2025-11-15T10:00:00";
        String endDate = "2025-11-17T10:00:00";

        String carsJson = "[{\"id\":1,\"type\":\"SUV\"},{\"id\":2,\"type\":\"SUV\"}]";
        mockServer.expect(requestTo("http://inventory-service/cars?type=SUV"))
                .andRespond(withSuccess(carsJson, MediaType.APPLICATION_JSON));

        when(reservationRepository.findByCarIdAndDateRange(anyLong(), any(LocalDateTime.class),
                any(LocalDateTime.class)))
                .thenReturn(Collections.emptyList());

        when(pricingStrategy.calculatePrice(2, "SUV")).thenReturn(200.0);

        List<Car> availableCars = availabilityQueryService.searchAvailableCars("SUV", startDate, endDate);

        mockServer.verify();
        assertEquals(2, availableCars.size(), "Both cars should be available");
        assertTrue(availableCars.stream().allMatch(c -> c.getPrice() == 200.0), "All cars should have correct price");
    }
}
