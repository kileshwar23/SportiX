package com.example.dream11backend.dto;

public class RegisterResponse {
    private Long id;
    private String username;
    private String email;
    private String message;
    
    public RegisterResponse() {}
    
    public RegisterResponse(Long id, String username, String email, String message) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.message = message;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private Long id;
        private String username;
        private String email;
        private String message;
        
        public Builder id(Long id) { this.id = id; return this; }
        public Builder username(String username) { this.username = username; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder message(String message) { this.message = message; return this; }
        
        public RegisterResponse build() {
            return new RegisterResponse(id, username, email, message);
        }
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

