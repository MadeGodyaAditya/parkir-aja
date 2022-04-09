package com.mandiri.service;

import com.mandiri.dto.CustomPage;
import com.mandiri.dto.ParkingOwnerSearchForm;
import com.mandiri.entity.ParkingOwner;
import com.mandiri.library.CustomException;
import com.mandiri.repository.ParkingOwnerRepository;
import com.mandiri.specification.ParkingOwnerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ParkingOwnerService implements CreateReadService<ParkingOwner, String>, UpdateService<ParkingOwner>, DeleteService<String> {
    @Autowired
    ParkingOwnerRepository parkingOwnerRepository;

    @Override
    public void checkId(String s){
        if(!parkingOwnerRepository.existsById(s)) {
            CustomException.throwNotFound(this.getClass().getSimpleName(), s);
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

    public CustomPage<ParkingOwner> find(ParkingOwnerSearchForm parkingOwnerSearchForm, Pageable pageable){
        ParkingOwnerSpecification parkingOwnerSpecification = new ParkingOwnerSpecification(parkingOwnerSearchForm);
        Page<ParkingOwner> pageData = parkingOwnerRepository.findAll(parkingOwnerSpecification, pageable);
        return new CustomPage<ParkingOwner>(pageData);
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
