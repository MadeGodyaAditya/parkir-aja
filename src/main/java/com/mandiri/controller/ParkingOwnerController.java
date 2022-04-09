package com.mandiri.controller;

import com.mandiri.entity.ParkingOwner;
import com.mandiri.service.ParkingOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking_owner")
public class ParkingOwnerController implements CreateReadController<ParkingOwner, String>, UpdateController<ParkingOwner>, DeleteController<String> {

    @Autowired
    ParkingOwnerService parkingOwnerService;


    @Override
    public ParkingOwner register(ParkingOwner parkingOwner) {
        return parkingOwnerService.register(parkingOwner);
    }

    @Override
    public ParkingOwner findById(String id) {
        return parkingOwnerService.findById(id);
    }

    @Override
    public ParkingOwner update(ParkingOwner parkingOwner) {
        return parkingOwnerService.update(parkingOwner);
    }

    @Override
    public void delete(String id) {
        parkingOwnerService.delete(id);
    }

}
