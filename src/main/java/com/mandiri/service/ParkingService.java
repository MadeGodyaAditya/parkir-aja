package com.mandiri.service;

import com.mandiri.entity.Parking;
import com.mandiri.library.CustomException;
import com.mandiri.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingService implements CreateReadService<Parking, String>, UpdateService<Parking>, DeleteService<String> {

    @Autowired
    ParkingRepository parkingRepository;

    @Override
    public void checkId(String s) {
        if(!parkingRepository.existsById(s)) {
            CustomException.ResponseThrow(this.getClass().getSimpleName(), s);
        }
    }

    @Override
    public Parking register(Parking parking) {
        return parkingRepository.save(parking);
    }

    @Override
    public Parking findById(String s) {
        checkId(s);

        return parkingRepository.findById(s).get();
    }

    @Override
    public Parking update(Parking parking) {
        checkId(parking.getId());
        return null;
    }

    @Override
    public void delete(String s) {
        checkId(s);

        parkingRepository.deleteById(s);
    }

}
