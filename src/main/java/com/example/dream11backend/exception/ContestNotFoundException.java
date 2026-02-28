package com.example.dream11backend.exception;

public class ContestNotFoundException extends RuntimeException {
    public ContestNotFoundException(String message) {
        super(message);
    }
    public ContestNotFoundException(Long id) {
        super("Contest not found with id: " + id);
    }
}

