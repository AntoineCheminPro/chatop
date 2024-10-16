package com.openclassrooms.api_chatop.exceptions;

public class RentalChangeOwnerForbiddenException extends RuntimeException {
    public RentalChangeOwnerForbiddenException(String message) {
        super(message);
    }
}
