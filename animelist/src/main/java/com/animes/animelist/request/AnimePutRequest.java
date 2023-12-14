package com.animes.animelist.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimePutRequest {
    private Long id;
    @NotEmpty(message = "Anime name cannot be empty")
    private String name;
    @NotNull(message = "Anime episode cannot be null")
    private Short episode;
    @NotEmpty(message = "Anime creator cannot be empty")
    private String creator;
    @NotNull(message = "Anime launch date cannot be null")
    private LocalDate launchDate;
}
