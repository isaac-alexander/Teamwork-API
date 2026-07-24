package com.alexander.teamwork_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class GifRequest {

    // Title of the GIF.
    @NotBlank
    private String title;

    // GIF file uploaded by the employee.
    @NotBlank
    private MultipartFile gif;

}