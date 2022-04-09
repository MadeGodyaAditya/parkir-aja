package com.mandiri.service;

import com.mandiri.dto.CustomPage;
import com.mandiri.dto.TimeSpentContent;
import com.mandiri.entity.Parking;
import com.mandiri.entity.ParkingLot;
import com.mandiri.library.CustomException;
import com.mandiri.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public Parking findById(String s) {
        checkId(s);

        return parkingRepository.findById(s).get();
    }

    public CustomPage<TimeSpentContent<Parking>> getTimeSpent(String id, Pageable pageable){
        List<Parking> parkingList = parkingRepository.findByParkingLotId(id);
        if (parkingList == null){
            CustomException.throwNotFound(this.getClass().getSimpleName(), id);
        }

        List<TimeSpentContent<Parking>> timeSpentContentList = new ArrayList<>();

        parkingList.forEach((n)->{
            long diff = System.currentTimeMillis() - (n.getEntrance().getTime());
            long hours = TimeUnit.MILLISECONDS.toHours(diff);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(diff) % 60;

            TimeSpentContent<Parking> timeSpentContent = new TimeSpentContent<>(n, hours + " Jam, " + minutes + " Menit, " + seconds + " Detik.");
            timeSpentContentList.add(timeSpentContent);
        });

        Page<TimeSpentContent<Parking>> pageData = new PageImpl<TimeSpentContent<Parking>>(timeSpentContentList, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), timeSpentContentList.size());
        return new CustomPage<TimeSpentContent<Parking>>(pageData);

    }

    @Override
    public void delete(String s) {
        checkId(s);

        parkingRepository.deleteById(s);
    }

}
