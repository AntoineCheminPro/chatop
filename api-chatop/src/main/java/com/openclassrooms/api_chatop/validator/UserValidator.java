package com.openclassrooms.api_chatop.validator;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openclassrooms.api_chatop.dto.UserDTO; 
import com.openclassrooms.api_chatop.dto.UserRegistrationDTO; 

public class UserValidator {

    private static final Logger log = LoggerFactory.getLogger(UserValidator.class);

    public static List<String> validate(UserRegistrationDTO userRegistrationDTO) {
        log.info("Début de l'enregistrement de l'utilisateur");
        // Log des données d'entrée
        log.info("Validation des données d'enregistrement : {}", userRegistrationDTO);

        List<String> errors = new ArrayList<>();
        if (userRegistrationDTO == null) {
            errors.add("L'utilisateur est obligatoire");
            errors.add("Le champ email est obligatoire");
            errors.add("Le champ mot de passe est obligatoire");
            return errors;
        }
        if (userRegistrationDTO.getName() == null || userRegistrationDTO.getName().isEmpty()) {   
            errors.add("Le champ nom est obligatoire");
        }
        if (userRegistrationDTO.getEmail() == null || userRegistrationDTO.getEmail().isEmpty()) {
            errors.add("Le champ email est obligatoire");
        }
        else if (!userRegistrationDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            errors.add("Email invalide");
        }
        if (userRegistrationDTO.getPassword() == null || userRegistrationDTO.getPassword().isEmpty()) {
            errors.add("Le champ mot de passe est obligatoire");
        }
        return errors;
    }

    public static List<String> validate(UserDTO userDTO) {
        List<String> errors = new ArrayList<>();
        if (userDTO == null) {
            errors.add("L'utilisateur est obligatoire");
            errors.add("Le champ email est obligatoire");
            errors.add("Le champ mot de passe est obligatoire");
            return errors;
        }
        if (userDTO.getName() == null || userDTO.getName().isEmpty()) {   
            errors.add("Le champ nom est obligatoire");
        }
        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            errors.add("Le champ email est obligatoire");
        }
        else if (!userDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            errors.add("Email invalide");
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            errors.add("Le champ mot de passe est obligatoire");
        }
        return errors;
    }
}
