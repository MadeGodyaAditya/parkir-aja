package com.mandiri.service;

import com.mandiri.dto.CustomPage;
import com.mandiri.dto.ParkingLotSearchForm;
import com.mandiri.entity.ParkingLot;
import com.mandiri.library.CustomException;
import com.mandiri.repository.ParkingLotRepository;
import com.mandiri.specification.ParkingLotSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService implements CreateReadService<ParkingLot, String>, UpdateService<ParkingLot>, DeleteService<String> {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Autowired
    ParkingOwnerService parkingOwnerService;

    @Override
    public void checkId(String s){
        if(!parkingLotRepository.existsById(s)) {
            CustomException.ResponseThrow(this.getClass().getSimpleName(), s);
        }
    }

    @Override
    public ParkingLot register(ParkingLot parkingLot) {
        parkingOwnerService.checkId(parkingLot.getOwnerId());

        return parkingLotRepository.save(parkingLot);
    }

    @Override
    public ParkingLot findById(String s) {
        checkId(s);

        return parkingLotRepository.findById(s).get();
    }

    public CustomPage<ParkingLot> find(ParkingLotSearchForm parkingLotSearchForm, Pageable pageable){
        ParkingLotSpecification parkingLotSpecification = new ParkingLotSpecification(parkingLotSearchForm);
        Page<ParkingLot> pageData = parkingLotRepository.findAll(parkingLotSpecification, pageable);
        return new CustomPage<ParkingLot>(pageData);
    }

    @Override
    public ParkingLot update(ParkingLot parkingLot) {
        checkId(parkingLot.getId());
        parkingOwnerService.checkId(parkingLot.getOwnerId());

        return parkingLotRepository.save(parkingLot);
    }

    @Override
    public void delete(String s) {
        checkId(s);

        parkingLotRepository.deleteById(s);
    }
}
