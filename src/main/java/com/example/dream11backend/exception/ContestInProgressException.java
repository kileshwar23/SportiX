package com.example.dream11backend.exception;

public class ContestInProgressException extends RuntimeException {
    public ContestInProgressException(String message) {
        super(message);
    }
    public ContestInProgressException(Long contestId) {
        super("Contest " + contestId + " is currently in progress");
    }
}

