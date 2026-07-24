package com.alexander.teamwork_api.service.impl;

import com.alexander.teamwork_api.service.GifService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GifServiceImpl implements GifService {

//    private final GifRepository gifRepository;
//    private final UserRepository userRepository;
//    private final FileStorageService fileStorageService;
//
//    // Uploads a GIF for the logged-in employee.
//    @Override
//    public GifResponse uploadGif(
//            GifRequest request,
//            Authentication authentication) {
//
//        // Gets the email of the logged-in user.
//        String email = authentication.getName();
//
//        // Finds the logged-in user.
//        User author = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UserNotFoundException("User not found"));
//
//        // Saves the uploaded GIF file.
//        String fileName = fileStorageService.saveFile(request.getGif());
//
//        // Creates a new GIF.
//        Gif gif = Gif.builder()
//                .title(request.getTitle())
//                .fileName(fileName)
//                .author(author)
//                .build();
//
//        // Saves the GIF information.
//        Gif savedGif = gifRepository.save(gif);
//
//        // Converts the entity into a response DTO.
//        return GifMapper.toGifResponse(savedGif);
//    }
//
//    // Returns GIFs ordered from newest to oldest.
//    @Override
//    public Page<GifResponse> getAllGifs(
//            int page,
//            int size) {
//
//        Pageable pageable = PageRequest.of(page, size);
//
//        return gifRepository
//                .findAllByOrderByCreatedAtDesc(pageable)
//                .map(GifMapper::toGifResponse);
//    }
//
//    // Returns one GIF using its ID.
//    @Override
//    public GifResponse getGifById(Long id) {
//
//        Gif gif = gifRepository.findById(id)
//                .orElseThrow(() -> new GifNotFoundException("GIF not found"));
//
//        return GifMapper.toGifResponse(gif);
//    }
//
//    // Returns an uploaded GIF file.
//    @Override
//    public Resource getGifFile(String fileName) {
//
//        return fileStorageService.loadFile(fileName);
//    }
//
//    // Deletes a GIF if it belongs to the logged-in employee.
//    @Override
//    public void deleteGif(
//            Long id,
//            Authentication authentication) {
//
//        // Gets the email of the logged-in user.
//        String email = authentication.getName();
//
//        // Finds the GIF.
//        Gif gif = gifRepository.findById(id)
//                .orElseThrow(() -> new GifNotFoundException("GIF not found"));
//
//        // Checks if the logged-in user owns the GIF.
//        if (!gif.getAuthor().getEmail().equals(email)) {
//            throw new UnauthorizedActionException("You can only delete your own GIF");
//        }
//
//        // Attempts to delete the uploaded GIF file.
//        boolean deleted = fileStorageService.deleteFile(gif.getFileName());
//
//        // Logs a warning if the file couldn't be deleted.
//        if (!deleted) {
//            System.out.println("Warning: The GIF file could not be deleted, but the database record will still be removed.");
//        }
//
//        // Deletes the GIF record from the database.
//        gifRepository.delete(gif);
//    }

}