package com.example.ParkAndRide.ParkAndRide.controller;

import com.example.ParkAndRide.ParkAndRide.entity.ParkingLocation;
import com.example.ParkAndRide.ParkAndRide.service.ParkingLocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parkingLocations")
public class ParkingLocationController {
    private final ParkingLocationService service;
    public ParkingLocationController(ParkingLocationService service) { this.service = service; }

    @GetMapping
    public List<ParkingLocation> getAll() { return service.findParking(); }


    @GetMapping("/{id}") public ResponseEntity<ParkingLocation> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping public ParkingLocation create(@RequestBody ParkingLocation parkingLocation) { return service.save(parkingLocation); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.deleteById(id); }
}