package com.openclassrooms.api_chatop.models.responses;

import lombok.Data;

@Data
public class SimpleOutputMessageResponse {

    private String Message;
    public SimpleOutputMessageResponse(String message) {
        Message = message;
    }
}
