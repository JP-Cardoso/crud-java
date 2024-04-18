package com.demo.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RequestExceptionsHandler {

  @SuppressWarnings("rawtypes")
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity threat404() {
    ExceptionDTO response = new ExceptionDTO("Data not found with provided ID", 404);
    return ResponseEntity.badRequest().body(response);
  }
}
