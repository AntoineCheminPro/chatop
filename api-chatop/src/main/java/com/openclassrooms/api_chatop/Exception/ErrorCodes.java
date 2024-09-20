package com.openclassrooms.api_chatop.Exception;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    USER_NOT_FOUND(1000),
    USER_NOT_VALID(1001),
    USER_ALREADY_EXISTS(1002),
    USER_NOT_FOUND_WITH_EMAIL(1003),
    USER_NOT_FOUND_WITH_ID(1004),
    USER_NOT_FOUND_WITH_USERNAME(1005), 

    RENTAL_NOT_FOUND(2000),
    RENTAL_NOT_VALID(2001),
    RENTAL_ALREADY_EXISTS(2002),
    RENTAL_NOT_FOUND_WITH_ID(2004),
    RENTAL_NOT_FOUND_WITH_NAME(2005),

    MESSAGE_NOT_FOUND(3000),
    MESSAGE_NOT_VALID(3001),
    MESSAGE_ALREADY_EXISTS(3002),
    MESSAGE_NOT_FOUND_WITH_ID(3004),
    MESSAGE_NOT_FOUND_WITH_NAME(3005);

    private final int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
