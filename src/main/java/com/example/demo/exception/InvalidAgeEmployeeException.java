package com.example.demo.exception;

public class InvalidAgeEmployeeException extends Exception{
    public InvalidAgeEmployeeException(String message) {
        super(message);
    }
}
