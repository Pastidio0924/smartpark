// src/main/java/com/smartpark/repository/VehicleRepository.java
package com.smartpark.repository;

import com.smartpark.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByLicensePlate(String licensePlate);

    List<Vehicle> findByParkingLot_Id(Long parkingLotId);
}