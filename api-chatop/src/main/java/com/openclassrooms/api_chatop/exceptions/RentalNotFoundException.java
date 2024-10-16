package com.openclassrooms.api_chatop.exceptions;

public class  RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException(String message) {
        super(message);
    }
}
