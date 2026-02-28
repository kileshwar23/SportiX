package com.example.dream11backend.exception;

public class ContestAlreadyStartedException extends RuntimeException {
    public ContestAlreadyStartedException(String message) {
        super(message);
    }
    public ContestAlreadyStartedException(Long contestId) {
        super("Contest " + contestId + " has already started");
    }
}

