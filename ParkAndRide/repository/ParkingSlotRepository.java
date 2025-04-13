package com.example.ParkAndRide.ParkAndRide.repository;

import com.example.ParkAndRide.ParkAndRide.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot,Long> {
}
