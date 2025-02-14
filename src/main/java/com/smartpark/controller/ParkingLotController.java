// src/main/java/com/smartpark/controller/ParkingLotController.java
package com.smartpark.controller;

import com.smartpark.model.ParkingLot;
import com.smartpark.service.ParkingLotService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-lots")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping
    public ResponseEntity<ParkingLot> registerParkingLot(@Valid @RequestBody ParkingLot parkingLot) {
        return new ResponseEntity<>(parkingLotService.registerParkingLot(parkingLot), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingLot> getParkingLotById(@PathVariable Long id) {
        return new ResponseEntity<>(parkingLotService.getParkingLotById(id), HttpStatus.OK);
    }

    @GetMapping("/lotId/{lotId}")
    public ResponseEntity<ParkingLot> getParkingLotByLotId(@PathVariable String lotId) {
        return new ResponseEntity<>(parkingLotService.getParkingLotByLotId(lotId), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<ParkingLot>> getAllParkingLots() {
        return new ResponseEntity<>(parkingLotService.getAllParkingLots(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingLot> updateParkingLot(@PathVariable Long id, @Valid @RequestBody ParkingLot parkingLotDetails) {
        return new ResponseEntity<>(parkingLotService.updateParkingLot(id, parkingLotDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteParkingLot(@PathVariable Long id) {
        parkingLotService.deleteParkingLot(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<Integer> getAvailableSpaces(@PathVariable Long id) {
        ParkingLot parkingLot = parkingLotService.getParkingLotById(id);
        return new ResponseEntity<>(parkingLotService.getAvailableSpaces(parkingLot), HttpStatus.OK);
    }
}