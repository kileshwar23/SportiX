package com.example.dream11backend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private Double balance;
    private Integer contestsJoined;
    private Integer contestsWon;
    private Double totalWinnings;
    
    // Manual builder method since Lombok may not be processing
    public static UserResponse builder() {
        return new UserResponse();
    }
    
    public UserResponse id(Long id) { this.id = id; return this; }
    public UserResponse username(String username) { this.username = username; return this; }
    public UserResponse email(String email) { this.email = email; return this; }
    public UserResponse fullName(String fullName) { this.fullName = fullName; return this; }
    public UserResponse balance(Double balance) { this.balance = balance; return this; }
    public UserResponse contestsJoined(Integer contestsJoined) { this.contestsJoined = contestsJoined; return this; }
    public UserResponse contestsWon(Integer contestsWon) { this.contestsWon = contestsWon; return this; }
    public UserResponse totalWinnings(Double totalWinnings) { this.totalWinnings = totalWinnings; return this; }
    
    public UserResponse build() {
        return this;
    }
}

