// src/main/java/com/smartpark/service/ParkingLotService.java
package com.smartpark.service;

import com.smartpark.exception.ParkingLotFullException;
import com.smartpark.exception.ResourceNotFoundException;
import com.smartpark.model.ParkingLot;
import com.smartpark.repository.ParkingLotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingLot registerParkingLot(ParkingLot parkingLot) {
        // You might want to add validation here (e.g., check if lotId already exists)
        return parkingLotRepository.save(parkingLot);
    }

    public ParkingLot getParkingLotById(Long id) {
        return parkingLotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parking Lot not found with id: " + id));
    }

    public ParkingLot getParkingLotByLotId(String lotId) {
        ParkingLot parkingLot = parkingLotRepository.findByLotId(lotId);
        if (parkingLot == null) {
            throw new ResourceNotFoundException("Parking Lot not found with lotId: " + lotId);
        }
        return parkingLot;
    }

    public List<ParkingLot> getAllParkingLots() {
        return parkingLotRepository.findAll();
    }


    public ParkingLot updateParkingLot(Long id, ParkingLot parkingLotDetails) {
        ParkingLot parkingLot = getParkingLotById(id); // Use the existing method to find the lot.

        parkingLot.setLotId(parkingLotDetails.getLotId());
        parkingLot.setLocation(parkingLotDetails.getLocation());
        parkingLot.setCapacity(parkingLotDetails.getCapacity());
        parkingLot.setOccupiedSpaces(parkingLotDetails.getOccupiedSpaces());

        return parkingLotRepository.save(parkingLot);
    }


    public void deleteParkingLot(Long id) {
        ParkingLot parkingLot = getParkingLotById(id);
        parkingLotRepository.delete(parkingLot);
    }

    public void incrementOccupiedSpaces(ParkingLot parkingLot) {
        if (parkingLot.getOccupiedSpaces() >= parkingLot.getCapacity()) {
            throw new ParkingLotFullException("Parking lot is full.");
        }
        parkingLot.setOccupiedSpaces(parkingLot.getOccupiedSpaces() + 1);
        parkingLotRepository.save(parkingLot);
    }

    public void decrementOccupiedSpaces(ParkingLot parkingLot) {
        parkingLot.setOccupiedSpaces(parkingLot.getOccupiedSpaces() - 1);
        parkingLotRepository.save(parkingLot);
    }

    public int getAvailableSpaces(ParkingLot parkingLot) {
        return parkingLot.getCapacity() - parkingLot.getOccupiedSpaces();
    }
}