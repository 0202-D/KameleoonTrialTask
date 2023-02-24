package com.kameleoon.dmitriypetrov.kameleoontrialtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler
        public ResponseEntity<NotFound> handleException(NotFoundException incorrectDataException) {
            NotFound notFound = new NotFound();
            notFound.setInfo(incorrectDataException.getMessage());
            return new ResponseEntity<>(notFound, HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler
        public ResponseEntity<IncorrectData> handleException(IncorrectDataException incorrectDataException) {
            IncorrectData incorrectData = new IncorrectData();
            incorrectData.setInfo(incorrectDataException.getMessage());
            return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
        }
    }

