package com.example.ParkAndRide.ParkAndRide.entity;

import com.example.ParkAndRide.ParkAndRide.enums.SlotStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "parking_slot")
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ParkingLocation parkingLocation;

    private String slotNumber;
    @Enumerated(EnumType.STRING)
    private SlotStatus status;

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }

    //    private Boolean sensorStatus;
//    private String rfidCode;
//    private String licensePlate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParkingLocation getParkingLocation() {
        return parkingLocation;
    }

    public void setParkingLocation(ParkingLocation parkingLocation) {
        this.parkingLocation = parkingLocation;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }



//    public Boolean getSensorStatus() {
//        return sensorStatus;
//    }
//
//    public void setSensorStatus(Boolean sensorStatus) {
//        this.sensorStatus = sensorStatus;
//    }
//
//    public String getRfidCode() {
//        return rfidCode;
//    }
//
//    public void setRfidCode(String rfidCode) {
//        this.rfidCode = rfidCode;
//    }
//
//    public String getLicensePlate() {
//        return licensePlate;
//    }
//
//    public void setLicensePlate(String licensePlate) {
//        this.licensePlate = licensePlate;
//    }
}
