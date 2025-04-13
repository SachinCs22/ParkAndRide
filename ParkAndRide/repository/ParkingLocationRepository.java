package com.example.ParkAndRide.ParkAndRide.repository;

import com.example.ParkAndRide.ParkAndRide.entity.ParkingLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLocationRepository extends JpaRepository<ParkingLocation,Long> {
}
