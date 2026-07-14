package com.alexander.teamwork_api.service;

import com.alexander.teamwork_api.dto.GifRequest;
import com.alexander.teamwork_api.dto.GifResponse;
import com.alexander.teamwork_api.entity.Gif;
import com.alexander.teamwork_api.entity.User;
import com.alexander.teamwork_api.exception.GifNotFoundException;
import com.alexander.teamwork_api.exception.UnauthorizedActionException;
import com.alexander.teamwork_api.exception.UserNotFoundException;
import com.alexander.teamwork_api.mapper.GifMapper;
import com.alexander.teamwork_api.repository.GifRepository;
import com.alexander.teamwork_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
@RequiredArgsConstructor
public class GifService {

    private final GifRepository gifRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    // Uploads a GIF for the logged-in employee.
    public GifResponse uploadGif(
            GifRequest request,
            Authentication authentication) {

        // Gets the email of the logged-in user.
        String email = authentication.getName();

        // Finds the logged-in user.
        User author = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Saves the uploaded GIF file.
        String fileName = fileStorageService.saveFile(request.getGif());

        // Creates a new GIF.
        Gif gif = Gif.builder()
                .title(request.getTitle())
                .fileName(fileName)
                .author(author)
                .build();

        // Saves the GIF information.
        Gif savedGif = gifRepository.save(gif);

        // Converts the entity into a response DTO.
        return GifMapper.toGifResponse(savedGif);
    }

    // Returns GIFs ordered from newest to oldest.
    public Page<GifResponse> getAllGifs(
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return gifRepository
                .findAllByOrderByCreatedAtDesc(pageable)
                .map(GifMapper::toGifResponse);
    }

    // Returns one GIF using its ID.
    public GifResponse getGifById(Long id) {

        Gif gif = gifRepository.findById(id)
                .orElseThrow(() -> new GifNotFoundException("GIF not found"));

        return GifMapper.toGifResponse(gif);
    }

    // Returns an uploaded GIF file.
    public Resource getGifFile(String fileName) {

        return fileStorageService.loadFile(fileName);
    }

    // Deletes a GIF if it belongs to the logged-in employee.
    public void deleteGif(
            Long id,
            Authentication authentication) {

        // Gets the email of the logged-in user.
        String email = authentication.getName();

        // Finds the GIF.
        Gif gif = gifRepository.findById(id)
                .orElseThrow(() -> new GifNotFoundException("GIF not found"));

        // Checks if the logged-in user owns the GIF.
        if (!gif.getAuthor().getEmail().equals(email)) {
            throw new UnauthorizedActionException("You can only delete your own GIF");
        }

        // Attempts to delete the uploaded GIF file.
        boolean deleted = fileStorageService.deleteFile(gif.getFileName());

        // Logs a warning if the file couldn't be deleted.
        if (!deleted) {
            System.out.println("Warning: The GIF file could not be deleted, but the database record will still be removed.");
        }

        // Deletes the GIF record from the database.
        gifRepository.delete(gif);
    }

}