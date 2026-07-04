package com.alexander.teamwork_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    // Saves an uploaded file and returns its new file name.
    public String saveFile(MultipartFile file) {

        try {

            Path uploadPath = Paths.get(uploadDir);

            // Creates the uploads folder if it doesn't exist.
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Creates a unique file name.
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            // Saves the file.
            Files.copy(
                    file.getInputStream(),
                    uploadPath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING
            );

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("Failed to save file.");
        }
    }

    // Returns an uploaded GIF file.
    public Resource loadFile(String fileName) {

        try {

            Path filePath = Paths.get(uploadDir).resolve(fileName);

            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                throw new RuntimeException("File not found");
            }

            return resource;

        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found");
        }

    }

    // Deletes an uploaded file.
// Returns true if the file was deleted or did not exist.
// Returns false if an error occurred while deleting.
    public boolean deleteFile(String fileName) {

        try {

            Path filePath = Paths.get(uploadDir).resolve(fileName);

            // If the file doesn't exist, there's nothing to delete.
            if (!Files.exists(filePath)) {
                return true;
            }

            Files.delete(filePath);

            return true;

        } catch (IOException e) {

            // Logs the error and allows the application to continue.
            System.err.println("Failed to delete file: " + fileName);
            e.printStackTrace();

            return false;
        }
    }

}