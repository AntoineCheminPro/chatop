package com.openclassrooms.api_chatop.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.api_chatop.controller.api.MessagesApi;
import com.openclassrooms.api_chatop.dto.MessagesDTO;
import com.openclassrooms.api_chatop.services.MessagesService;

@RestController
public class MessagesController implements MessagesApi {

    @Autowired
    private final MessagesService messagesService;
    //Getter injection
    public MessagesService getMessagesService() {
        return messagesService;
    }

    // constructor injection
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }
    

    @Override
    public MessagesDTO findById(Integer id) {
        return messagesService.findById(id);
    }

    @Override
    public List<MessagesDTO> findAll() {
        return messagesService.findAll();
    }

    @Override
    public MessagesDTO save(MessagesDTO messagesDTO) {
        return messagesService.save(messagesDTO);
    }

    @Override
    public void delete(Integer id) {
        messagesService.delete(id);
    }

}
