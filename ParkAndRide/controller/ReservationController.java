package com.example.ParkAndRide.ParkAndRide.controller;

import com.example.ParkAndRide.ParkAndRide.entity.ParkingSlot;
import com.example.ParkAndRide.ParkAndRide.entity.Reservation;
import com.example.ParkAndRide.ParkAndRide.enums.SlotStatus;
import com.example.ParkAndRide.ParkAndRide.repository.ParkingSlotRepository;
import com.example.ParkAndRide.ParkAndRide.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService service;
    public ReservationController(ReservationService service) { this.service = service;
    }

    @GetMapping
    public List<Reservation> getAll() { return service.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Reservation> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Reservation create(@RequestBody Reservation reservation) {
        return service.save(reservation);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.deleteById(id); }
}