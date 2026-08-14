package com.kameleoon.dmitriypetrov.kameleoontrialtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFound> handleNotFoundException(NotFoundException exception) {
        NotFound notFound = new NotFound();
        notFound.setInfo(exception.getMessage());
        return new ResponseEntity<>(notFound, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<IncorrectData> handleIncorrectDataException(IncorrectDataException exception) {
        IncorrectData incorrectData = new IncorrectData();
        incorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }
}

