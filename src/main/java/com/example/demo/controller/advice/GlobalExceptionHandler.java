package com.example.demo.controller.advice;

import com.example.demo.exceptions.InvalidUpdateEmployeeException;
import com.example.demo.exceptions.InvalidAgeEmployeeException;
import com.example.demo.exceptions.InvalidSalaryEmployeeException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseException exceptionHandler(Exception e) {
        return new ResponseException(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseException handlerArgumentNotValid(MethodArgumentNotValidException exception) {
        String errorMessage = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(" | "));

        return new ResponseException(errorMessage);
    }

    @ExceptionHandler({InvalidUpdateEmployeeException.class, InvalidAgeEmployeeException.class, InvalidSalaryEmployeeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseException exceptionHandler2(Exception e) {
        return new ResponseException(e.getMessage());
    }
}