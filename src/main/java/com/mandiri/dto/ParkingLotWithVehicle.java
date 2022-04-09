package com.mandiri.dto;

import com.mandiri.entity.Parking;
import com.mandiri.entity.ParkingLot;

import java.util.List;

public class ParkingLotWithVehicle {
    private final ParkingLot parkingLot;
    private final Integer parkedVehicle;

    public ParkingLotWithVehicle(ParkingLot parkingLot, Integer parkedVehicle) {
        this.parkingLot = parkingLot;
        this.parkedVehicle = parkedVehicle;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public Integer getParkedVehicle() {
        return parkedVehicle;
    }
}
