package com.alexander.teamwork_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

}