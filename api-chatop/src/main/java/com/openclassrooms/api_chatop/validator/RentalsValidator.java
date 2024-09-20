package com.openclassrooms.api_chatop.validator;
import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.api_chatop.dto.RentalsDTO;

public class RentalsValidator {
    public static List<String> validate(RentalsDTO rentalDTO) {
        List<String> errors = new ArrayList<>();
        if (rentalDTO == null) {
            errors.add("Le nom est obligatoire");
            errors.add("La description est obligatoire");
            errors.add("La surface est obligatoire");
            errors.add("Le prix est obligatoire");
            return errors;
        }
        if (rentalDTO.getName() == null || rentalDTO.getName().isEmpty()) {
            errors.add("Le nom est obligatoire");
        }
        if (rentalDTO.getDescription() == null || rentalDTO.getDescription().isEmpty()) {
            errors.add("La description est obligatoire");
        }
        if(rentalDTO.getSurface() == null) {
            errors.add("La surface est obligatoire");
        }
        if (rentalDTO.getPrice() == null) {
            errors.add("Le prix est obligatoire");
        }
        return errors;
    }
}
