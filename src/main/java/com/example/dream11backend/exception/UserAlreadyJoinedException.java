package com.example.dream11backend.exception;

public class UserAlreadyJoinedException extends RuntimeException {
    public UserAlreadyJoinedException(String message) {
        super(message);
    }
    public UserAlreadyJoinedException(String username, Long contestId) {
        super("User " + username + " has already joined contest " + contestId);
    }
}

