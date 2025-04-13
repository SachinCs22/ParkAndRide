package com.example.ParkAndRide.ParkAndRide.service;

import com.example.ParkAndRide.ParkAndRide.entity.ParkingSlot;
import com.example.ParkAndRide.ParkAndRide.entity.Reservation;
import com.example.ParkAndRide.ParkAndRide.enums.ReservationStatus;
import com.example.ParkAndRide.ParkAndRide.enums.SlotStatus;
import com.example.ParkAndRide.ParkAndRide.repository.ParkingSlotRepository;
import com.example.ParkAndRide.ParkAndRide.repository.ReservationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationMonitorService {
    private final ReservationRepository reservationRepository;
    private final ParkingSlotRepository parkingSlotRepository;

    public ReservationMonitorService(ReservationRepository reservationRepository, ParkingSlotRepository parkingSlotRepository) {
        this.reservationRepository = reservationRepository;
        this.parkingSlotRepository = parkingSlotRepository;
    }

    @Scheduled(fixedRate = 60000) // runs every 60 seconds
    public void releaseExpiredReservations() {
        LocalDateTime now = LocalDateTime.now();

        List<Reservation> endedReservations = reservationRepository.findByStatusAndEndTimeBefore(
                ReservationStatus.ACTIVE, now
        );

        for (Reservation reservation : endedReservations) {
            ParkingSlot slot = reservation.getParkingSlot();
            slot.setStatus(SlotStatus.AVAILABLE);
            reservation.setStatus(ReservationStatus.COMPLETED);

            parkingSlotRepository.save(slot);
            reservationRepository.save(reservation);

            System.out.println("Slot " + slot.getSlotNumber() + " is now AVAILABLE.");
        }
    }
}
