package com.mandiri.repository;

import com.mandiri.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String>{
    List<Parking> findByParkingLotId(String parkingLotId);
    Parking findByLicensePlate(String licensePlate);
    Integer countByType(String s);
}
