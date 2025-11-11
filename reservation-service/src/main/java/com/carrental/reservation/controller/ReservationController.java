package com.carrental.reservation.controller;

import com.carrental.reservation.domain.Reservation;
import com.carrental.reservation.service.ReservationCommandService;
import com.carrental.reservation.service.ReservationQueryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationCommandService commandService;
    private final ReservationQueryService queryService;

    @Autowired
    public ReservationController(ReservationCommandService commandService, ReservationQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return queryService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return queryService.getReservationById(id);
    }

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        //Check availability logic can be added here
        //Add logic to calculate price based on pricing strategy and make payment.
        // You can add price to reservation if needed
        return commandService.createReservation(reservation);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        return commandService.updateReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        commandService.deleteReservation(id);
    }
}
