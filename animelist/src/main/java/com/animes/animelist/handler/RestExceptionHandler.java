package com.animes.animelist.handler;

import com.animes.animelist.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException exception){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .title("Bad Request")
                        .detail(exception.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .developerMessage(exception.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String fields = exception.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessages = exception.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .title("Check the fields")
                        .detail(exception.getTitleMessageCode())
                        .status(status.value())
                        .developerMessage(exception.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .fields(fields)
                        .fieldMessages(fieldMessages)
                        .build(),
                status
        );
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DataIntegrityViolationExceptionDetails> HandleDataIntegrityViolationException(DataIntegrityViolationException exception){
        return new ResponseEntity<>(DataIntegrityViolationExceptionDetails.builder()
                .title("Data persistence error")
                .detail(exception.getCause().getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .developerMessage(exception.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build(),
                HttpStatus.BAD_REQUEST);
    }
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title(exception.getMessage())
                .detail(exception.getCause().getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .developerMessage(BadRequestException.class.getName())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails ,statusCode);
    }
}
