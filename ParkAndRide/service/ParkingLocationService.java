package com.example.ParkAndRide.ParkAndRide.service;

import com.example.ParkAndRide.ParkAndRide.entity.ParkingLocation;
import com.example.ParkAndRide.ParkAndRide.repository.ParkingLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingLocationService {

    private final ParkingLocationRepository parkingLocationRepository;

    public ParkingLocationService(ParkingLocationRepository parkingLocationRepository) {
        this.parkingLocationRepository = parkingLocationRepository;
    }

    public List<ParkingLocation> findAll() {
        return parkingLocationRepository.findAll();
    }

    public Optional<ParkingLocation> findById(Long id) {
        return parkingLocationRepository.findById(id);
    }

    public ParkingLocation save(ParkingLocation parkingLocation) {
        return parkingLocationRepository.save(parkingLocation);
    }

        public List<ParkingLocation> findParking() {
            return parkingLocationRepository.findAll().stream()
                    .filter(parkingLocation -> {
                        if (parkingLocation.getLatitude() == null || parkingLocation.getLongitude() == null) {
                            return false;
                        }
                        double pickupDistance = calculateDistance(
                                28.6139,
                                77.209,
                                parkingLocation.getLatitude(),
                                parkingLocation.getLongitude());
                        return pickupDistance <= 1.0;
                    })
                    .collect(Collectors.toList());
        }

    private double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        final int R = 6371; // Earth's radius in km.
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }


    public void deleteById(Long id) {
        parkingLocationRepository.deleteById(id);
    }
}
