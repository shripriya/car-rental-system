package com.carrental.reservation.repository;

import com.carrental.reservation.domain.Reservation;
import java.util.*;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(Long id);
    List<Reservation> findAll();
    void deleteById(Long id);
    Reservation update(Reservation reservation);
}
