package com.animes.animelist.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
    private String title;
    private String detail;
    private int status;
    private String developerMessage;
    private LocalDateTime timestamp;
}
