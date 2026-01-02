package com.example.AutoHub.exception;

public class ConcurrencyException extends RuntimeException{
    public ConcurrencyException(String message){
        super(message);
    }
}
