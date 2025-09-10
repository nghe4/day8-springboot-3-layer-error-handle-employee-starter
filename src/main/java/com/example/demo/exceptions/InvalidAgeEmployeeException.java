package com.example.demo.exceptions;

public class InvalidAgeEmployeeException extends Exception{
    public InvalidAgeEmployeeException(String message) {
        super(message);
    }
}