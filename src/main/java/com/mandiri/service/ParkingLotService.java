package com.mandiri.service;

import com.mandiri.entity.ParkingLot;
import com.mandiri.library.CustomException;
import com.mandiri.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService implements CreateReadService<ParkingLot, String>, UpdateService<ParkingLot>, DeleteService<String> {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Override
    public void checkId(String s){
        if(!parkingLotRepository.existsById(s)) {
            CustomException.ResponseThrow(this.getClass().getSimpleName(), s);
        }
    }

    @Override
    public ParkingLot register(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    @Override
    public ParkingLot findById(String s) {
        checkId(s);

        return parkingLotRepository.findById(s).get();
    }

    @Override
    public ParkingLot update(ParkingLot parkingLot) {
        checkId(parkingLot.getId());

        return parkingLotRepository.save(parkingLot);
    }

    @Override
    public void delete(String s) {
        checkId(s);

        parkingLotRepository.deleteById(s);
    }
}
