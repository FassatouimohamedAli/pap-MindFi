package com.example.projetmindfit.Handler;

import com.example.projetmindfit.Exceptions.MeditantEmailExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloblexceptionHandler {

    @ExceptionHandler(MeditantEmailExistException.class)
    public ResponseEntity<String> MeditantEmailExistException(MeditantEmailExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
