// src/main/java/com/smartpark/repository/ParkingLotRepository.java
package com.smartpark.repository;

import com.smartpark.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

    ParkingLot findByLotId(String lotId);

}
