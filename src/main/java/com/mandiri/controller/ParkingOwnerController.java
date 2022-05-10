package com.mandiri.controller;

import com.mandiri.dto.CustomPage;
import com.mandiri.dto.ParkingOwnerSearchForm;
import com.mandiri.entity.ParkingOwner;
import com.mandiri.service.ParkingOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    
    @GetMapping
    public CustomPage<ParkingOwner> searchByParam(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(required = false) String idCard,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phoneNumber){
        Pageable pageable = PageRequest.of(page, size);
        return parkingOwnerService.find(new ParkingOwnerSearchForm(idCard, name, address, phoneNumber), pageable);

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
