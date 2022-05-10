package com.mandiri.service;

import com.mandiri.dto.BillContent;
import com.mandiri.dto.CustomPage;
import com.mandiri.dto.TimeSpentContent;
import com.mandiri.entity.Fee;
import com.mandiri.entity.Parking;
import com.mandiri.entity.ParkingLot;
import com.mandiri.entity.Report;
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
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class ParkingService implements CreateReadService<Parking, String> {

    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    ParkingLotService parkingLotService;
    @Autowired
    FeeService feeService;
    @Autowired
    ReportService reportService;

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
    private void checkParentValue(Parking parking, ParkingLot parkingLot) {
        if(!Objects.equals(parkingLot.getCategory(), "All")){
            if(!Objects.equals(parking.getType(), parkingLot.getCategory())){
                CustomException.throwNotAcceptable("Category", parking.getType());
            }
        }
    }
    private void checkDuplicateLicensePlate(Parking parking) {
        if (parkingRepository.findByLicensePlate(parking.getLicensePlate()) != null){
            CustomException.throwDuplicateValue(parking.getLicensePlate(), "Parking Lot");
        }
    }

    @Override
    @Transactional
    public Parking register(Parking parking) {
        ParkingLot parkingLot = parkingLotService.findById(parking.getParkingLotId());
        checkParkingAvailability(parkingLot);
        checkParentValue(parking, parkingLot);
        checkDuplicateLicensePlate(parking);

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

    public Parking findByLicensePlate(String s){

        if(parkingRepository.findByLicensePlate(s) == null) {
            CustomException.throwNotFound(this.getClass().getSimpleName(), s);
        }

        return parkingRepository.findByLicensePlate(s);
    }

    public List<Parking> findByParkingLot(String s){
        return parkingRepository.findByParkingLotId(s);
    }

    public Integer countVehicle(String s){
        return parkingRepository.countByType(s);
    }

    public CustomPage<TimeSpentContent<Parking>> getTimeSpent(String id, Pageable pageable){
        List<Parking> parkingList = parkingRepository.findByParkingLotId(id);
        if (parkingList == null){
            CustomException.throwNotFound(this.getClass().getSimpleName(), id);
        }

        List<TimeSpentContent<Parking>> timeSpentContentList = new ArrayList<>();

        addToTimeSpentContentList(parkingList, timeSpentContentList);

        Page<TimeSpentContent<Parking>> pageData = new PageImpl<TimeSpentContent<Parking>>(timeSpentContentList, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), timeSpentContentList.size());
        return new CustomPage<TimeSpentContent<Parking>>(pageData);

    }
    private void addToTimeSpentContentList(List<Parking> parkingList, List<TimeSpentContent<Parking>> timeSpentContentList) {
        parkingList.forEach((n)->{
            long diff = System.currentTimeMillis() - (n.getEntrance().getTime());
            long hours = (long) Math.floor(TimeUnit.MILLISECONDS.toHours(diff));
            long minutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(diff) % 60;

            TimeSpentContent<Parking> timeSpentContent = new TimeSpentContent<>(n, hours + " Jam, " + minutes + " Menit, " + seconds + " Detik.");
            timeSpentContentList.add(timeSpentContent);
        });
    }

    public BillContent<Parking> getBill(String id){
        checkId(id);
        Parking parking = findById(id);
        Fee fee = feeService.findByParkingLotIdAndCategory(parking.getParkingLotId(), parking.getType());
        long diff = System.currentTimeMillis() - (parking.getEntrance().getTime());

        long hours = (long) Math.ceil(diff / (60.0*60.0*1000.0));
        Integer totalFee = Math.toIntExact(hours * fee.getFee());
        return new BillContent<Parking>(parking, new Timestamp(System.currentTimeMillis()), totalFee);
    }

    @Transactional
    public Report exitParking(String id){
        BillContent<Parking> billContent = getBill(id);
        Fee fee = feeService.findByParkingLotIdAndCategory(billContent.getT().getParkingLotId(), billContent.getT().getType());
        long diff = System.currentTimeMillis() - (billContent.getT().getEntrance().getTime());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);

        Report report = new Report(
                billContent.getT().getParkingLotId(),
                billContent.getT().getLicensePlate(),
                billContent.getT().getEntrance(),
                billContent.getExitTime(),
                minutes,
                fee.getFee(),
                billContent.getPrice()
        );
        parkingRepository.deleteById(id);
        return reportService.register(report);
    }

}
