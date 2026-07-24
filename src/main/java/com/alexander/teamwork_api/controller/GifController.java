package com.alexander.teamwork_api.controller;

import com.alexander.teamwork_api.dto.GifRequest;
import com.alexander.teamwork_api.dto.GifResponse;
import com.alexander.teamwork_api.service.GifService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/gifs")
@RequiredArgsConstructor
public class GifController {
//
//    private final GifService gifService;
//
//    // Uploads a new GIF.
//    @PostMapping(consumes = "multipart/form-data")
//    public GifResponse uploadGif(
//            @RequestParam String title,
//            @RequestParam MultipartFile gif,
//            Authentication authentication) {
//
//        GifRequest request = new GifRequest(title, gif);
//
//        return gifService.uploadGif(request, authentication);
//    }
//
//    // Returns GIFs using pagination.
//    @GetMapping
//    public Page<GifResponse> getAllGifs(
//
//            @RequestParam(defaultValue = "0") int page,
//
//            @RequestParam(defaultValue = "5") int size) {
//
//        return gifService.getAllGifs(page, size);
//    }
//
//    // Returns an uploaded GIF file.
//    @GetMapping("/files/{fileName}")
//    public ResponseEntity<Resource> getGifFile(
//            @PathVariable String fileName) {
//
//        Resource resource = gifService.getGifFile(fileName);
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_GIF)
//                .header(
//                        HttpHeaders.CONTENT_DISPOSITION,
//                        "inline; filename=\"" + fileName + "\""
//                )
//                .body(resource);
//    }
//
//    // Deletes a GIF.
//    @DeleteMapping("/{id}")
//    public String deleteGif(
//            @PathVariable Long id,
//            Authentication authentication) {
//
//        gifService.deleteGif(id, authentication);
//
//        return "GIF deleted successfully.";
//    }

}