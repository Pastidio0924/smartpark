// src/test/java/com/smartpark/SmartparkApplicationTests.java
package com.smartpark;

import com.smartpark.model.ParkingLot;
import com.smartpark.model.Vehicle;
import com.smartpark.model.VehicleType;
import com.smartpark.service.ParkingLotService;
import com.smartpark.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SmartparkApplicationTests {

    @Autowired
    private ParkingLotService parkingLotService;

    @Autowired
    private VehicleService vehicleService;

    @Test
    void contextLoads() {
        // Basic test to check if the application context loads successfully
    }

    @Test
    void testRegisterParkingLot() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setLotId("TEST-LOT");
        parkingLot.setLocation("Test Location");
        parkingLot.setCapacity(50);
        parkingLot.setOccupiedSpaces(0);

        ParkingLot registeredLot = parkingLotService.registerParkingLot(parkingLot);

        assertNotNull(registeredLot.getId());
        assertEquals("TEST-LOT", registeredLot.getLotId());
    }

    @Test
    void testCheckInVehicle() {
        // Create a parking lot
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setLotId("TEST-LOT2");
        parkingLot.setLocation("Test Location 2");
        parkingLot.setCapacity(1); // Small capacity for this test
        parkingLot.setOccupiedSpaces(0);
        ParkingLot registeredLot = parkingLotService.registerParkingLot(parkingLot);

        //Create a vehicle
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("TEST-VEHICLE");
        vehicle.setType(VehicleType.CAR);
        vehicle.setOwnerName("Test Owner");
        vehicleService.registerVehicle(vehicle);

        // Check in the vehicle
        vehicleService.checkInVehicle("TEST-VEHICLE", "TEST-LOT2");

        // Verify that the vehicle is parked in the lot
        Vehicle checkedInVehicle = vehicleService.getVehicleByLicensePlate("TEST-VEHICLE");
        assertEquals(registeredLot.getId(), checkedInVehicle.getParkingLot().getId());

        // Verify that the parking lot's occupied spaces have been updated
        ParkingLot updatedLot = parkingLotService.getParkingLotById(registeredLot.getId());
        assertEquals(1, updatedLot.getOccupiedSpaces());

        // Try to check-in the same vehicle in a different lot.
        assertThrows(Exception.class, () -> vehicleService.checkInVehicle("TEST-VEHICLE", "TEST-LOT2"));
    }
}