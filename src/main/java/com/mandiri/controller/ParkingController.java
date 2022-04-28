package com.mandiri.controller;

import com.mandiri.dto.BillContent;
import com.mandiri.dto.CustomPage;
import com.mandiri.dto.TimeSpentContent;
import com.mandiri.entity.Parking;
import com.mandiri.entity.Report;
import com.mandiri.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController implements CreateReadController<Parking, String>{
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

    @CrossOrigin
    @GetMapping("/{parkingLotId}/all/time_spent")
    public CustomPage<TimeSpentContent<Parking>> getTimeSpent(
            @PathVariable String parkingLotId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return parkingService.getTimeSpent(parkingLotId, pageable);
    }

    @CrossOrigin
    @GetMapping("/{id}/bill")
    public BillContent<Parking> getBill(@PathVariable String id){
        return parkingService.getBill(id);
    }

    @DeleteMapping()
    public Report exitParking(@RequestParam String id){
        return parkingService.exitParking(id);
    }
}
