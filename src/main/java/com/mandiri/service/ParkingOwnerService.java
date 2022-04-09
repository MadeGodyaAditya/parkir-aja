package com.mandiri.service;

import com.mandiri.entity.ParkingLot;
import com.mandiri.entity.ParkingOwner;
import com.mandiri.library.CustomException;
import com.mandiri.repository.ParkingOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingOwnerService implements CreateReadService<ParkingOwner, String>, UpdateService<ParkingOwner>, DeleteService<String> {
    @Autowired
    ParkingOwnerRepository parkingOwnerRepository;

    @Override
    public void checkId(String s){
        if(!parkingOwnerRepository.existsById(s)) {
            CustomException.ResponseThrow(this.getClass().getSimpleName(), s);
        }
    }

    @Override
    public ParkingOwner register(ParkingOwner parkingOwner) {
        return parkingOwnerRepository.save(parkingOwner);
    }

    @Override
    public ParkingOwner findById(String s) {
        checkId(s);

        return parkingOwnerRepository.findById(s).get();
    }

    @Override
    public ParkingOwner update(ParkingOwner parkingOwner) {
        checkId(parkingOwner.getId());

        return parkingOwnerRepository.save(parkingOwner);
    }

    @Override
    public void delete(String s) {
        checkId(s);

        parkingOwnerRepository.deleteById(s);
    }
}
