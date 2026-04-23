package com.example.dream11backend.service.dto;

public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private Double balance;
    private Integer contestsJoined;
    private Integer contestsWon;
    private Double totalWinnings;

    public UserResponse() {}

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final UserResponse obj = new UserResponse();
        public Builder id(Long v) { obj.id = v; return this; }
        public Builder username(String v) { obj.username = v; return this; }
        public Builder email(String v) { obj.email = v; return this; }
        public Builder fullName(String v) { obj.fullName = v; return this; }
        public Builder balance(Double v) { obj.balance = v; return this; }
        public Builder contestsJoined(Integer v) { obj.contestsJoined = v; return this; }
        public Builder contestsWon(Integer v) { obj.contestsWon = v; return this; }
        public Builder totalWinnings(Double v) { obj.totalWinnings = v; return this; }
        public UserResponse build() { return obj; }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
    public Integer getContestsJoined() { return contestsJoined; }
    public void setContestsJoined(Integer contestsJoined) { this.contestsJoined = contestsJoined; }
    public Integer getContestsWon() { return contestsWon; }
    public void setContestsWon(Integer contestsWon) { this.contestsWon = contestsWon; }
    public Double getTotalWinnings() { return totalWinnings; }
    public void setTotalWinnings(Double totalWinnings) { this.totalWinnings = totalWinnings; }
}
