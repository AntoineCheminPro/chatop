package com.openclassrooms.api_chatop.controller.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.api_chatop.dto.RentalsDTO;
import static com.openclassrooms.api_chatop.utils.Constants.APP_ROOT;

public interface RentalsApi {
    
    @GetMapping(value = APP_ROOT + "/rentals/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    RentalsDTO findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/rentals/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RentalsDTO> findAll();

    @PostMapping(value = APP_ROOT + "/rentals", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    RentalsDTO save(RentalsDTO rentalsDTO);

    @PostMapping(value = APP_ROOT + "/rentals/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file);

    @DeleteMapping(value = APP_ROOT + "/rentals/{id}")
    void delete(@PathVariable Integer id);

}
