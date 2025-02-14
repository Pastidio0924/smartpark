// src/main/java/com/smartpark/service/VehicleService.java
package com.smartpark.service;

import com.smartpark.exception.ResourceNotFoundException;
import com.smartpark.exception.VehicleAlreadyParkedException;
import com.smartpark.model.ParkingLot;
import com.smartpark.model.Vehicle;
import com.smartpark.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ParkingLotService parkingLotService;

    public VehicleService(VehicleRepository vehicleRepository, ParkingLotService parkingLotService) {
        this.vehicleRepository = vehicleRepository;
        this.parkingLotService = parkingLotService;
    }

    public Vehicle registerVehicle(Vehicle vehicle) {
        if (vehicleRepository.findByLicensePlate(vehicle.getLicensePlate()) != null) {
            throw new IllegalArgumentException("Vehicle with license plate already exists.");
        }
        return vehicleRepository.save(vehicle);
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
    }

    public Vehicle getVehicleByLicensePlate(String licensePlate) {
        Vehicle vehicle = vehicleRepository.findByLicensePlate(licensePlate);
        if (vehicle == null) {
            throw new ResourceNotFoundException("Vehicle not found with license plate: " + licensePlate);
        }
        return vehicle;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        Vehicle vehicle = getVehicleById(id);

        vehicle.setLicensePlate(vehicleDetails.getLicensePlate());
        vehicle.setType(vehicleDetails.getType());
        vehicle.setOwnerName(vehicleDetails.getOwnerName());
        vehicle.setParkingLot(vehicleDetails.getParkingLot());

        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        Vehicle vehicle = getVehicleById(id);
        vehicleRepository.delete(vehicle);
    }


    public void checkInVehicle(String licensePlate, String lotId) {
        Vehicle vehicle = getVehicleByLicensePlate(licensePlate);
        ParkingLot parkingLot = parkingLotService.getParkingLotByLotId(lotId);

        if (vehicle.getParkingLot() != null) {
            throw new VehicleAlreadyParkedException("Vehicle is already parked in another lot.");
        }

        parkingLotService.incrementOccupiedSpaces(parkingLot);
        vehicle.setParkingLot(parkingLot);
        vehicleRepository.save(vehicle);
    }

    public void checkOutVehicle(String licensePlate) {
        Vehicle vehicle = getVehicleByLicensePlate(licensePlate);

        if (vehicle.getParkingLot() == null) {
            throw new IllegalStateException("Vehicle is not currently parked.");
        }

        ParkingLot parkingLot = vehicle.getParkingLot();
        vehicle.setParkingLot(null);  // Remove the vehicle from the lot
        parkingLotService.decrementOccupiedSpaces(parkingLot);
        vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehiclesInLot(Long parkingLotId) {
        return vehicleRepository.findByParkingLot_Id(parkingLotId);
    }
}