package com.alexander.teamwork_api.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class GifRequest {

    // Title of the GIF.
    private String title;

    // GIF file uploaded by the employee.
    private MultipartFile gif;

}