package com.openclassrooms.api_chatop.services;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.openclassrooms.api_chatop.Exception.EntityNotFoundException;
import com.openclassrooms.api_chatop.Exception.ErrorCodes;
import com.openclassrooms.api_chatop.Exception.InvalidEntityException;
import com.openclassrooms.api_chatop.dto.MessagesDTO;
import com.openclassrooms.api_chatop.model.Messages;
import com.openclassrooms.api_chatop.repository.MessagesRepository;
import com.openclassrooms.api_chatop.validator.MessagesValidator;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class MessagesService {
    
    private final MessagesRepository messagesRepository;

    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public MessagesDTO save(MessagesDTO messagesDTO){
        List<String> errors = MessagesValidator.validate(messagesDTO);
        if (!errors.isEmpty()) {
            log.error("Erreur lors de l'enregistrement du message {}", messagesDTO);
            throw new InvalidEntityException("Le message n'est pas valide", ErrorCodes.MESSAGE_NOT_VALID, errors);
        }

        Messages messages = messagesRepository.save(messagesDTO.toEntity(messagesDTO));
        log.info("Enregistrement du message {}", messagesDTO);
        return MessagesDTO.fromEntity(messages);
    };

    public MessagesDTO findById(Integer id){
        if(id == null){
            log.error("L'id du message est null");
            return null;
        }
        Optional<Messages> messages = messagesRepository.findById(id);
        if(messages.isPresent()){
            return MessagesDTO.fromEntity(messages.get());
        }
        throw new EntityNotFoundException("Le message avec l'id " + id + " n'existe pas", ErrorCodes.MESSAGE_NOT_FOUND);
    };
    
    public List<MessagesDTO> findAll(){
        log.info("Recherche de tous les messages");
        return messagesRepository.findAll().stream()
            .map(MessagesDTO::fromEntity)
            .collect(Collectors.toList());
    };
    
    public void delete(Integer id){
        if(id == null){
            log.error("L'id du message est null");
            return;
        }
        messagesRepository.deleteById(id);
        log.info("Le message avec l'id " + id + " a été supprimé");
    };
}
