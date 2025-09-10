package com.example.demo.exceptions;

public class InvalidUpdateEmployeeException extends Exception{
    public InvalidUpdateEmployeeException(String message) {
        super(message);
    }
}
