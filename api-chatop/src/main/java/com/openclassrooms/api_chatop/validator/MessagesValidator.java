package com.openclassrooms.api_chatop.validator;
import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.api_chatop.dto.MessagesDTO;

public class MessagesValidator {
    public static List<String> validate(MessagesDTO messagesDTO) {
        List<String> errors = new ArrayList<>();
        if (messagesDTO == null) {
            errors.add("Le message est obligatoire");
            return errors;
        }
        if (messagesDTO.getMessage() == null || messagesDTO.getMessage().isEmpty()) {
            errors.add("Le message est obligatoire");
        }
        return errors;
    }
}
