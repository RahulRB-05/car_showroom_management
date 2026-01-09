package com.example.AutoHub.exception;

public class ReportGenerationFailedException extends RuntimeException{
    public ReportGenerationFailedException(String message){
        super(message);
    }
}
