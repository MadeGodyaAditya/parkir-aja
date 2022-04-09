package com.mandiri.service;

import com.mandiri.entity.Parking;
import com.mandiri.entity.ParkingLot;
import com.mandiri.library.CustomException;
import com.mandiri.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Service
public class ParkingService implements CreateReadService<Parking, String>, DeleteService<String> {

    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    ParkingLotService parkingLotService;
    @Autowired
    FeeService feeService;

    @Override
    public void checkId(String s) {
        if(!parkingRepository.existsById(s)) {
            CustomException.throwNotFound(this.getClass().getSimpleName(), s);
        }
    }
    private void checkParkingAvailability(ParkingLot parkingLot) {
        if((parkingLot.getCapacity() - 1) <= 0){
            CustomException.throwItemEmpty(this.getClass().getSimpleName());
        }
    }

    @Override
    @Transactional
    public Parking register(Parking parking) {
        ParkingLot parkingLot = parkingLotService.findById(parking.getParkingLotId());
        checkParkingAvailability(parkingLot);

        Timestamp transactionTime = new Timestamp(System.currentTimeMillis());
        parking.setEntrance(transactionTime);
        parkingLot.setCapacity(parkingLot.getCapacity()-1);

        parkingLotService.register(parkingLot);
        return parkingRepository.save(parking);
    }

    @Override
    @Transactional
    public Parking findById(String s) {
        checkId(s);

        return parkingRepository.findById(s).get();
    }

    @Override
    public void delete(String s) {
        checkId(s);

        parkingRepository.deleteById(s);
    }

}
