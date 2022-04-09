package com.mandiri.repository;

import com.mandiri.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<Fee, String> {
    Fee findByParkingLotIdAndCategory(String id, String category);
}
