package com.carrental.reservation.service;

import com.carrental.reservation.domain.Reservation;
import com.carrental.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationCommandService {
    private final ReservationRepository reservationRepository;
    public ReservationCommandService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    public Reservation updateReservation(Long id, Reservation reservation) {
        reservation.setId(id);
        return reservationRepository.update(reservation);
    }
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
