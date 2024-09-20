package com.openclassrooms.api_chatop.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    private final String uploadDir = "src/main/resources/static/images";

    public String uploadImage(MultipartFile file) {
        try {
            Files.createDirectories(Paths.get(uploadDir));

            Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
            file.transferTo(filePath.toFile());

            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'upload de l'image", e);
        }
    }
}