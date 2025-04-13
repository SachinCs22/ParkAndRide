package com.example.ParkAndRide.ParkAndRide.service;


import com.example.ParkAndRide.ParkAndRide.entity.ParkingSlot;
import com.example.ParkAndRide.ParkAndRide.repository.ParkingLocationRepository;
import com.example.ParkAndRide.ParkAndRide.repository.ParkingSlotRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ParkingSlotService {
    private final ParkingSlotRepository parkingSlotRepository;
    private final ParkingLocationRepository parkingLocationRepository;

    public ParkingSlotService(ParkingSlotRepository parkingSlotRepository, ParkingLocationRepository parkingLocationRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
        this.parkingLocationRepository = parkingLocationRepository;
    }


    public List<ParkingSlot> getSlots(Long id){
        List<ParkingSlot> slots= new ArrayList<>();
        for(ParkingSlot slot:parkingSlotRepository.findAll()){
            if(slot.getParkingLocation()!=null && slot.getParkingLocation().getId()!=null && Objects.equals(slot.getParkingLocation().getId(), id) && slot.getStatus().toString().equals("AVAILABLE")){
                slots.add(slot);
            }
        }
        return slots;
    }


    public List<ParkingSlot> findAll() {
        return parkingSlotRepository.findAll();
    }

    public Optional<ParkingSlot> findById(Long id) {
        return parkingSlotRepository.findById(id);
    }

    public ParkingSlot save(ParkingSlot slot) {
        return parkingSlotRepository.save(slot);
    }

    public void deleteById(Long id) {
        parkingSlotRepository.deleteById(id);
    }
}

