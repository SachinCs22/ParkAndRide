package com.example.ParkAndRide.ParkAndRide.repository;

import com.example.ParkAndRide.ParkAndRide.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
