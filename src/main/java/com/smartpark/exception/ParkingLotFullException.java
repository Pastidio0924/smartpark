// src/main/java/com/smartpark/exception/ParkingLotFullException.java
package com.smartpark.exception;

public class ParkingLotFullException extends RuntimeException {
    public ParkingLotFullException(String message) {
        super(message);
    }
}