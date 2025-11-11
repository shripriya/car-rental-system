package com.carrental.reservation.domain;

import java.time.LocalDateTime;
import com.carrental.reservation.domain.ReservationStatus;
import com.carrental.common.domain.CarType;
public class Reservation {
    private Long id;
    private Long userId;
    private Long carId;
    private CarType carType; // User preferred car type
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ReservationStatus status;

    public Reservation() {}

    public Reservation(Long id, Long userId, Long carId, CarType carType, LocalDateTime startDate, LocalDateTime endDate, ReservationStatus status) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.carType = carType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
