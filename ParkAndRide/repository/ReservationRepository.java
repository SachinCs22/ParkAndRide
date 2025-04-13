package com.example.ParkAndRide.ParkAndRide.repository;

import com.example.ParkAndRide.ParkAndRide.entity.Reservation;
import com.example.ParkAndRide.ParkAndRide.enums.ReservationStatus;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByStatusAndEndTimeBefore(ReservationStatus status, LocalDateTime time);

}
