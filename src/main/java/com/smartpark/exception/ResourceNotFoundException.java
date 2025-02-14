// src/main/java/com/smartpark/exception/ResourceNotFoundException.java
package com.smartpark.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}