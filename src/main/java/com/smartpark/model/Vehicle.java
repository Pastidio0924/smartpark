// src/main/java/com/smartpark/model/Vehicle.java
package com.smartpark.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9-]+$")  // Letters, numbers, and dashes only
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Pattern(regexp = "^[a-zA-Z\\s]+$") // Letters and spaces only
    private String ownerName;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot; // Null if not parked
}