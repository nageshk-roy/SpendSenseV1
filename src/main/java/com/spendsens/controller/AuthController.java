package com.spendsens.controller;

import com.spendsens.dto.AuthDtos.*;
import com.spendsens.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest req) {
        return ResponseEntity.ok(authService.refreshToken(req));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(Authentication auth) {
        authService.logout((Long) auth.getPrincipal());
        return ResponseEntity.noContent().build();
    }

    // Fetch Logged-in User Details
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUserDetails(Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        return ResponseEntity.ok(authService.getUserDetails(userId));
    }

    // Delete Logged-in User
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        authService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}