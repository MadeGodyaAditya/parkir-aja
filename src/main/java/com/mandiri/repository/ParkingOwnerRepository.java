package com.mandiri.repository;

import com.mandiri.entity.ParkingOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingOwnerRepository extends JpaRepository<ParkingOwner, String> {
}
