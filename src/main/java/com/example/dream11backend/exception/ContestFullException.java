package com.example.dream11backend.exception;

public class ContestFullException extends RuntimeException {
    public ContestFullException(String message) {
        super(message);
    }
    public ContestFullException(Long contestId) {
        super("Contest " + contestId + " is full");
    }
}

