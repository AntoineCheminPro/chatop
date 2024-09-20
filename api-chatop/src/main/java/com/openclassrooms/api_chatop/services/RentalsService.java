package com.openclassrooms.api_chatop.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.openclassrooms.api_chatop.Exception.EntityNotFoundException;
import com.openclassrooms.api_chatop.Exception.ErrorCodes;
import com.openclassrooms.api_chatop.Exception.InvalidEntityException;
import com.openclassrooms.api_chatop.dto.RentalsDTO;
import com.openclassrooms.api_chatop.model.Rentals;
import com.openclassrooms.api_chatop.repository.RentalsRepository;
import com.openclassrooms.api_chatop.validator.RentalsValidator;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class RentalsService {

    private final RentalsRepository rentalsRepository;
        
    public RentalsService(RentalsRepository rentalsRepository) {
        this.rentalsRepository = rentalsRepository;
    }

    public RentalsDTO save(RentalsDTO rentalsDTO){
        List<String> errors = RentalsValidator.validate(rentalsDTO);
        if (!errors.isEmpty()) {
            log.error("Erreur lors de l'enregistrement de la location {}", rentalsDTO);
            throw new InvalidEntityException("La location n'est pas valide", ErrorCodes.RENTAL_NOT_VALID, errors);
        }
        
        Rentals rentals = rentalsRepository.save(rentalsDTO.toEntity(rentalsDTO));
        log.info("La location est enregistrée avec succès");

        return RentalsDTO.fromEntity(rentals);
    };

    public RentalsDTO findById(Integer id){
        if(id == null){
            log.error("L'id de la location est null");
            return null;
        }
        Optional<Rentals> rentals = rentalsRepository.findById(id);
        if(rentals.isPresent()){
            return RentalsDTO.fromEntity(rentals.get());
        }
        throw new EntityNotFoundException("La location avec l'id " + id + " n'existe pas", ErrorCodes.RENTAL_NOT_FOUND);
    };

    public List<RentalsDTO> findAll(){
        return rentalsRepository.findAll().stream()
            .map(RentalsDTO::fromEntity)
            .collect(Collectors.toList());
    };

    public void delete(Integer id){
        if(id == null){
            log.error("L'id de la location est null");
            return;
        }
        rentalsRepository.deleteById(id);
        log.info("La location avec l'id " + id + " a été supprimée");
    };

}
