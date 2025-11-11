package com.carrental.reservation.service;

import com.carrental.reservation.domain.Reservation;
import com.carrental.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationQueryService {
    private final ReservationRepository reservationRepository;
    public ReservationQueryService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
