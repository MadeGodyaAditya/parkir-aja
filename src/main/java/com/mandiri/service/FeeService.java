package com.mandiri.service;

import com.mandiri.entity.Fee;
import com.mandiri.entity.ParkingLot;
import com.mandiri.library.CustomException;
import com.mandiri.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FeeService implements CreateReadService<Fee, String>, UpdateService<Fee>, DeleteService<String>{
    @Autowired
    FeeRepository feeRepository;
    @Autowired
    ParkingLotService parkingLotService;

    @Override
    public void checkId(String s) {
        if(!feeRepository.existsById(s)) {
            CustomException.throwNotFound(this.getClass().getSimpleName(), s);
        }
    }
    private void checkParentValue(Fee fee, ParkingLot parkingLot) {
        if (!Objects.equals(parkingLot.getCategory(), "All")){
            if(!Objects.equals(parkingLot.getCategory(), fee.getCategory())){
                CustomException.throwNotAcceptable("Category", fee.getCategory());
            }
        }
    }

    @Override
    public Fee register(Fee fee) {
        ParkingLot parkingLot = parkingLotService.findById(fee.getParkingLotId());
        checkParentValue(fee, parkingLot);

        return feeRepository.save(fee);
    }

    @Override
    public Fee findById(String s) {
        checkId(s);

        return feeRepository.findById(s).get();
    }

    @Override
    public Fee update(Fee fee) {
        checkId(fee.getId());
        ParkingLot parkingLot = parkingLotService.findById(fee.getParkingLotId());
        checkParentValue(fee, parkingLot);

        return feeRepository.save(fee);
    }

    @Override
    public void delete(String s) {
        checkId(s);

        feeRepository.deleteById(s);
    }
}
