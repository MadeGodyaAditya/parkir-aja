package com.mandiri.controller;

import com.mandiri.dto.CustomPage;
import com.mandiri.dto.ParkingLotSearchForm;
import com.mandiri.dto.ParkingLotWithVehicle;
import com.mandiri.entity.ParkingLot;
import com.mandiri.service.ParkingLotParkingCrossService;
import com.mandiri.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/parking_lot")
public class ParkingLotController implements CreateReadController<ParkingLot, String>, UpdateController<ParkingLot>, DeleteController<String> {
    @Autowired
    ParkingLotService parkingLotService;
    @Autowired
    ParkingLotParkingCrossService parkingLotParkingCrossService;

    @Override
    public ParkingLot register(ParkingLot parkingLot) {
        return parkingLotService.register(parkingLot);
    }

    @Override
    public ParkingLot findById(String id) {
        return parkingLotService.findById(id);
    }

    @GetMapping
    public CustomPage<ParkingLot> searchByParam(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(required = false) String ownerId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer capacityBottomLimit,
            @RequestParam(required = false) Integer capacityTopLimit,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer feeBottomLimit,
            @RequestParam(required = false) Integer feeTopLimit){
        Pageable pageable = PageRequest.of(page, size);
        return parkingLotService.find(new ParkingLotSearchForm(ownerId, name, address, capacityBottomLimit, capacityTopLimit,
                category,feeBottomLimit,feeTopLimit), pageable);

    }

    @GetMapping("/{id}/vehicle_list")
    public ParkingLotWithVehicle getParkingLotVehicle(@PathVariable String id) {
        return  parkingLotParkingCrossService.parkingLotWithVehicle(id);
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
