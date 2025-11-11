package com.carrental.reservation.repository;

import com.carrental.reservation.domain.Reservation;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryReservationRepository implements ReservationRepository {
    private final Map<Long, Reservation> reservations = new ConcurrentHashMap<>();
    private long nextId = 1;

    @Override
    public Reservation save(Reservation reservation) {
        reservation.setId(nextId++);
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return Optional.ofNullable(reservations.get(id));
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(reservations.values());
    }

    @Override
    public void deleteById(Long id) {
        reservations.remove(id);
    }

    @Override
    public Reservation update(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }
}
