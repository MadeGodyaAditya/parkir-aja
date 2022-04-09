package com.mandiri.service;

import com.mandiri.dto.ParkingLotWithVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotParkingCrossService {
    @Autowired
    ParkingLotService parkingLotService;
    @Autowired
    ParkingService parkingService;

    public ParkingLotWithVehicle parkingLotWithVehicle(String id){
        return new ParkingLotWithVehicle(parkingLotService.findById(id), parkingService.findByParkingLot(id).size());
    }
}
