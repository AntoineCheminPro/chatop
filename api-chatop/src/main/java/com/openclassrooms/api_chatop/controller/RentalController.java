package com.openclassrooms.api_chatop.controller;

import com.openclassrooms.api_chatop.models.Rental;
import com.openclassrooms.api_chatop.models.requests.RentalRequest;
import com.openclassrooms.api_chatop.models.responses.RentalResponse;
import com.openclassrooms.api_chatop.models.responses.SimpleOutputMessageResponse;
import com.openclassrooms.api_chatop.services.RentalService;
import com.openclassrooms.api_chatop.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.openclassrooms.api_chatop.models.User;

@RestController
@RequestMapping("rentals")
public class RentalController{

    private final RentalService rentalService;
    private final UserService userService;

    public RentalController(RentalService rentalService, UserService userService) {
        this.rentalService = rentalService;
        this.userService = userService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAll() {
        var rentalsResponse = new HashMap<String, Object>();
        var rentals = rentalService.getRentals()
                .stream()
                .map(rentalService::buildRentalResponse)
                .collect(Collectors.toCollection(ArrayList::new));

        rentalsResponse.put("rentals", rentals);
        return new ResponseEntity<>(rentalsResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalResponse> getRentalById(@PathVariable("id") final Integer id) {
        var rental = rentalService.getRental(id);
        return new ResponseEntity<>(rentalService.buildRentalResponse(rental), HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRental(@Valid @ModelAttribute RentalRequest rentalRequest, HttpServletRequest request) throws IOException {
        // Récupérer l'email de l'utilisateur à partir de l'attribut de la requête
        String userEmail = (String) request.getAttribute("currentUser_email");
        
        // Utiliser l'email pour récupérer l'utilisateur
        User owner = userService.getUserByEmail(userEmail);
        
        // Logique pour créer un rental
        String pictureUrl = rentalService.saveFile(rentalRequest.getPicture());
        Rental rental = Rental.builder()
                .name(rentalRequest.getName())
                .surface(rentalRequest.getSurface())
                .price(rentalRequest.getPrice())
                .picture(pictureUrl)
                .description(rentalRequest.getDescription())
                .owner(owner)  
                .build();
        rentalService.saveOrUpdateRental(rental);
        
        return new ResponseEntity<>(new SimpleOutputMessageResponse("Rental created !"), HttpStatus.CREATED);
    }
        
    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRental(@PathVariable("id") final Integer id, @Valid @ModelAttribute RentalRequest rentalRequest) throws IOException {
        var rental = rentalService.getRental(id);
        if(StringUtils.isNotEmpty(rentalRequest.getName()) && !rental.getName().equals(rentalRequest.getName())) {
            rental.setName(rentalRequest.getName());
        }
        if(rentalRequest.getPicture() != null) {
            var pictureUrl = rentalService.saveFile(rentalRequest.getPicture());
            rental.setPicture(pictureUrl);
        }
        if(StringUtils.isNotEmpty(rentalRequest.getDescription()) && !rental.getDescription().equals(rentalRequest.getDescription())) {
            rental.setDescription(rentalRequest.getDescription());
        }
        if(rentalRequest.getPrice() != null && !rental.getPrice().equals(rentalRequest.getPrice())) {
            rental.setPrice(rentalRequest.getPrice());
        }
        if(rentalRequest.getSurface() != null && !rental.getSurface().equals(rentalRequest.getSurface())) {
            rental.setSurface(rentalRequest.getSurface());
        }

        rentalService.saveOrUpdateRental(rental);
        return new ResponseEntity<>(new SimpleOutputMessageResponse("Rental updated !"), HttpStatus.OK);
    }
}
