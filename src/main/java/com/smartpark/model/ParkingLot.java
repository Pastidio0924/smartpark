// src/main/java/com/smartpark/model/ParkingLot.java
package com.smartpark.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50)
    @Size(max = 50)
    private String lotId;

    private String location;

    private int capacity;

    private int occupiedSpaces;
}