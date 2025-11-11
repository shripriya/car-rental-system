package com.carrental.reservation.service;

import com.carrental.reservation.domain.Reservation;
import com.carrental.reservation.domain.ReservationStatus;
import com.carrental.reservation.repository.InMemoryReservationRepository;
import org.junit.jupiter.api.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationCommandServiceTest {
    private ReservationCommandService commandService;

    @BeforeEach
    void setUp() {
        commandService = new ReservationCommandService(new InMemoryReservationRepository());
    }

    @Test
    void testCreateAndDeleteReservation() {
        Reservation reservation = new Reservation(null, 1L, 1L, null, LocalDateTime.now(), LocalDateTime.now().plusDays(2), ReservationStatus.RESERVED);
        Reservation saved = commandService.createReservation(reservation);
        assertNotNull(saved.getId());
        commandService.deleteReservation(saved.getId());
        // No direct get method in commandService, but repository would return null
    }

    @Test
    void testUpdateReservation() {
        Reservation reservation = new Reservation(null, 2L, 2L, null, LocalDateTime.now(), LocalDateTime.now().plusDays(3), ReservationStatus.RESERVED);
        Reservation saved = commandService.createReservation(reservation);
        saved.setStatus(ReservationStatus.CANCELLED);
        Reservation updated = commandService.updateReservation(saved.getId(), saved);
        assertEquals(ReservationStatus.CANCELLED, updated.getStatus());
    }
}
