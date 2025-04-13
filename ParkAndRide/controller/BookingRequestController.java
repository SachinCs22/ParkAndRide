package com.example.ParkAndRide.ParkAndRide.controller;

import com.example.ParkAndRide.ParkAndRide.entity.BookingRequest;
import com.example.ParkAndRide.ParkAndRide.entity.ParkingSlot;
import com.example.ParkAndRide.ParkAndRide.entity.Reservation;
import com.example.ParkAndRide.ParkAndRide.entity.User;
import com.example.ParkAndRide.ParkAndRide.enums.ReservationStatus;
import com.example.ParkAndRide.ParkAndRide.enums.SlotStatus;
import com.example.ParkAndRide.ParkAndRide.service.ParkingSlotService;
import com.example.ParkAndRide.ParkAndRide.service.ReservationService;
import com.example.ParkAndRide.ParkAndRide.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/booking")
public class BookingRequestController {
    private final ReservationService reservationService;
    private final ParkingSlotService parkingSlotService;
    private final UserService userService;

    public BookingRequestController(ReservationService reservationService, ParkingSlotService parkingSlotService, UserService userService) {
        this.reservationService = reservationService;
        this.parkingSlotService = parkingSlotService;
        this.userService = userService;
    }

    @PostMapping("/reserve")
    public ResponseEntity<Reservation> bookSlot(@RequestBody BookingRequest request) {
        Optional<User> userOpt = userService.findById(request.getUserId());
        Optional<ParkingSlot> slotOpt = parkingSlotService.findById(request.getSlotId());

        if (userOpt.isEmpty() || slotOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ParkingSlot slot = slotOpt.get();
        if (!slot.getStatus().equals(SlotStatus.AVAILABLE)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        slot.setStatus(SlotStatus.valueOf(String.valueOf(SlotStatus.RESERVED)));
        parkingSlotService.save(slot);

        Reservation reservation = new Reservation();
        reservation.setUser(userOpt.get());
        reservation.setParkingSlot(slot);
        reservation.setStartTime(LocalDateTime.parse(request.getStart()));
        reservation.setEndTime(LocalDateTime.parse(request.getEnd()));
        reservation.setStatus(ReservationStatus.valueOf(String.valueOf(ReservationStatus.ACTIVE)));
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());

        return ResponseEntity.ok(reservationService.save(reservation));
    }
}