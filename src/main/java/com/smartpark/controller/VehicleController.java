// src/main/java/com/smartpark/controller/VehicleController.java
package com.smartpark.controller;

import com.smartpark.model.Vehicle;
import com.smartpark.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<Vehicle> registerVehicle(@Valid @RequestBody Vehicle vehicle) {
        return new ResponseEntity<>(vehicleService.registerVehicle(vehicle), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return new ResponseEntity<>(vehicleService.getVehicleById(id), HttpStatus.OK);
    }

    @GetMapping("/license/{licensePlate}")
    public ResponseEntity<Vehicle> getVehicleByLicensePlate(@PathVariable String licensePlate) {
        return new ResponseEntity<>(vehicleService.getVehicleByLicensePlate(licensePlate), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @Valid @RequestBody Vehicle vehicleDetails) {
        return new ResponseEntity<>(vehicleService.updateVehicle(id, vehicleDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/checkin/{licensePlate}/{lotId}")
    public ResponseEntity<HttpStatus> checkInVehicle(@PathVariable String licensePlate, @PathVariable String lotId) {
        vehicleService.checkInVehicle(licensePlate, lotId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/checkout/{licensePlate}")
    public ResponseEntity<HttpStatus> checkOutVehicle(@PathVariable String licensePlate) {
        vehicleService.checkOutVehicle(licensePlate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/lot/{parkingLotId}")
    public ResponseEntity<List<Vehicle>> getAllVehiclesInLot(@PathVariable Long parkingLotId) {
        return new ResponseEntity<>(vehicleService.getAllVehiclesInLot(parkingLotId), HttpStatus.OK);
    }
}