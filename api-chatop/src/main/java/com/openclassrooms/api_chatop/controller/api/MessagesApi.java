package com.openclassrooms.api_chatop.controller.api;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.openclassrooms.api_chatop.dto.MessagesDTO;
import static com.openclassrooms.api_chatop.utils.Constants.APP_ROOT;

public interface MessagesApi {
    
    @GetMapping(value = APP_ROOT + "/messages/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    MessagesDTO findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/messages/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<MessagesDTO> findAll();

    @PostMapping(value = APP_ROOT + "/messages", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MessagesDTO save(MessagesDTO messagesDTO);

    @DeleteMapping(value = APP_ROOT + "/messages/{id}")
    void delete(@PathVariable Integer id);

}
