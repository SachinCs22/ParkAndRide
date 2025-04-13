package com.example.ParkAndRide.ParkAndRide.controller;


import com.example.ParkAndRide.ParkAndRide.entity.ParkingLocation;
import com.example.ParkAndRide.ParkAndRide.entity.ParkingSlot;
import com.example.ParkAndRide.ParkAndRide.entity.User;
import com.example.ParkAndRide.ParkAndRide.service.ParkingLocationService;
import com.example.ParkAndRide.ParkAndRide.service.ParkingSlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/slots")
public class ParkingSlotController {
    private final ParkingSlotService service;
    private final ParkingLocationService parkingLocationService;
    public ParkingSlotController(ParkingSlotService service, ParkingLocationService parkingLocationService) {
        this.service = service;
        this.parkingLocationService = parkingLocationService;
    }

    @GetMapping("/parking/{id}")
    public List<ParkingSlot> slots(@PathVariable Long id) { return service.getSlots(id); }
    @GetMapping("/{id}") public ResponseEntity<ParkingSlot> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ParkingSlot create(@RequestBody ParkingSlot slot) {
        Optional<ParkingLocation> parkingLocation = parkingLocationService.findById(slot.getParkingLocation().getId());
        ParkingSlot parkingSlot=new ParkingSlot();
        parkingSlot.setParkingLocation(parkingLocation.get());
        parkingSlot.setId(slot.getId());
        parkingSlot.setSlotNumber(slot.getSlotNumber());
        parkingSlot.setStatus(slot.getStatus());
        return service.save(parkingSlot);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.deleteById(id); }
}