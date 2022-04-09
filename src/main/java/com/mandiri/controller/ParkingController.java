package com.mandiri.controller;

import com.mandiri.entity.Parking;
import com.mandiri.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking")
public class ParkingController implements CreateReadController<Parking, String>, DeleteController<String>{
    @Autowired
    ParkingService parkingService;

    @Override
    public Parking register(Parking parking) {
        return parkingService.register(parking);
    }

    @Override
    public Parking findById(String id) {
        return parkingService.findById(id);
    }

    @Override
    public void delete(String id) {
        parkingService.delete(id);
    }
}
