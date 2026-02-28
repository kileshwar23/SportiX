package com.example.dream11backend.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
    public InsufficientBalanceException(Double required, Double available) {
        super("Insufficient balance. Required: " + required + ", Available: " + available);
    }
}

