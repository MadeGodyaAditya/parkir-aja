package com.mandiri.controller;

import com.mandiri.entity.ParkingLot;
import com.mandiri.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/parking_lot")
public class ParkingLotController implements CreateReadController<ParkingLot, String>, UpdateController<ParkingLot>, DeleteController<String> {
    @Autowired
    ParkingLotService parkingLotService;

    @Override
    public ParkingLot register(ParkingLot parkingLot) {
        return parkingLotService.register(parkingLot);
    }

    @Override
    public ParkingLot findById(String id) {
        return parkingLotService.findById(id);
    }

    @Override
    public ParkingLot update(ParkingLot parkingLot) {
        return parkingLotService.update(parkingLot);
    }

    @Override
    public void delete(String id) {
        parkingLotService.delete(id);
    }
}
