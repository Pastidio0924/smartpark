package com.smartpark.config;

import com.smartpark.model.ParkingLot;
import com.smartpark.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final ParkingLotRepository parkingLotRepository;

    @Autowired
    public DatabaseInitializer(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (parkingLotRepository.count() == 0) {
            // Create some parking lots
            ParkingLot lot1 = new ParkingLot();
            lot1.setLotId("LOT-001");
            lot1.setLocation("Downtown");
            lot1.setCapacity(100);
            lot1.setOccupiedSpaces(0);
            parkingLotRepository.save(lot1);

            ParkingLot lot2 = new ParkingLot();
            lot2.setLotId("LOT-002");
            lot2.setLocation("Uptown");
            lot2.setCapacity(50);
            lot2.setOccupiedSpaces(0);
            parkingLotRepository.save(lot2);

            System.out.println("Parking lot data initialized.");
        } else {
            System.out.println("Parking lot data already exists. Skipping initialization.");
        }
    }
}
