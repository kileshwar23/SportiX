package com.example.dream11backend.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEvent {

    // Event types
    public static final String USER_REGISTERED     = "USER_REGISTERED";
    public static final String CONTEST_JOINED      = "CONTEST_JOINED";
    public static final String CONTEST_LEFT        = "CONTEST_LEFT";
    public static final String CONTEST_CREATED     = "CONTEST_CREATED";
    public static final String CONTEST_DELETED     = "CONTEST_DELETED";
    public static final String INSUFFICIENT_BALANCE = "INSUFFICIENT_BALANCE";

    private String eventType;       // e.g. CONTEST_JOINED
    private String username;        // recipient
    private String email;           // recipient email
    private String message;         // human-readable message
    private Long   contestId;       // optional — related contest
    private String contestName;     // optional — contest name
    private Double amount;          // optional — fee / prize amount
    private LocalDateTime timestamp;
}
