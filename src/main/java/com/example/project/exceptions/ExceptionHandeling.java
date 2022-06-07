package com.example.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.Valid;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandeling {
    @ExceptionHandler(value = {ApiRequestExeption.class})
    public ResponseEntity<Object> handleResourceNotFound(ApiRequestExeption e){
        HttpStatus badRequest = HttpStatus.NOT_FOUND;
        ApiExep apiExep = new ApiExep(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiExep,badRequest);
    }
    @ExceptionHandler(value = {BadRequest.class})
    public ResponseEntity<Object> BadRequest(BadRequest e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiExep apiExep = new ApiExep(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiExep,badRequest);
    }
}
