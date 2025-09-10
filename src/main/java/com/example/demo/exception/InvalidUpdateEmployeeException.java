package com.example.demo.exception;

public class InvalidUpdateEmployeeException extends Exception{
    public InvalidUpdateEmployeeException(String message) {
        super(message);
    }
}
