package com.example.dream11backend.controller;

import com.example.dream11backend.dto.LoginRequest;
import com.example.dream11backend.dto.RegisterRequest;
import com.example.dream11backend.dto.RegisterResponse;
import com.example.dream11backend.service.AuthService;
import com.example.dream11backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = authService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            String token = jwtUtil.generateToken(authentication.getName());
            String refreshToken = jwtUtil.generateRefreshToken(authentication.getName());

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "refreshToken", refreshToken,
                    "expiresIn", jwtUtil.getExpirationTime(),
                    "username", authentication.getName()
            ));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid username or password"));
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        authService.initiatePasswordReset(email);
        // Always return success to avoid leaking whether the email exists
        return ResponseEntity.ok(Map.of("message", "If that email is registered, a reset link has been sent"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> body) {
        authService.resetPassword(body.get("token"), body.get("newPassword"));
        return ResponseEntity.ok(Map.of("message", "Password reset successfully"));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> body,
                                             Authentication authentication) {
        authService.changePassword(authentication.getName(), body.get("newPassword"));
        return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
    }

    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam String token) {
        authService.verifyEmail(token);
        return ResponseEntity.ok(Map.of("message", "Email verified successfully"));
    }
}
