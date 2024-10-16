package com.openclassrooms.api_chatop.services;

import org.springframework.stereotype.Service;

import com.openclassrooms.api_chatop.models.Message;
import com.openclassrooms.api_chatop.repositories.MessageRepository;

import lombok.Data;

@Data
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }
}
