package com.example.dream11backend.exception;

public class UserNotInContestException extends RuntimeException {
    public UserNotInContestException(String message) {
        super(message);
    }
    public UserNotInContestException(String username, Long contestId) {
        super("User " + username + " is not in contest " + contestId);
    }
}

