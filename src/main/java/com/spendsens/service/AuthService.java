package com.spendsens.service;

import com.spendsens.dto.AuthDtos.*;
import com.spendsens.model.RefreshToken;
import com.spendsens.model.User;
import com.spendsens.repository.RefreshTokenRepository;
import com.spendsens.repository.UserRepository;
import com.spendsens.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            RefreshTokenRepository refreshTokenRepository,
            JwtService jwtService,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // REGISTER
    // =========================
    public AuthResponse register(RegisterRequest req) {

        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail().trim().toLowerCase())
                .password(passwordEncoder.encode(req.getPassword()))
                .provider("LOCAL")
                .build();

        userRepository.save(user);

        return buildAuthResponse(user);
    }

    // =========================
    // LOGIN
    // =========================
    public AuthResponse login(LoginRequest req) {

        User user = userRepository.findByEmail(
                        req.getEmail().trim().toLowerCase()
                )
                .orElseThrow(() ->
                        new BadCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(
                req.getPassword(),
                user.getPassword())) {

            throw new BadCredentialsException("Invalid email or password");
        }

        return buildAuthResponse(user);
    }

    // =========================
    // REFRESH TOKEN
    // =========================
    public AuthResponse refreshToken(RefreshTokenRequest req) {

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(req.getRefreshToken())
                .orElseThrow(() ->
                        new RuntimeException("Invalid refresh token"));

        if (refreshToken.isExpired()) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }

        return buildAuthResponse(refreshToken.getUser());
    }

    // =========================
    // LOGOUT
    // =========================
    public void logout(Long userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }

    // =========================
    // BUILD RESPONSE
    // =========================
    private AuthResponse buildAuthResponse(User user) {

        String accessToken =
                jwtService.generateToken(
                        user.getId(),
                        user.getEmail()
                );

        String refreshToken =
                generateRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(jwtService.getExpiration())
                .user(
                        UserDto.builder()
                                .id(user.getId())
                                .name(user.getName())
                                .email(user.getEmail())
                                .photoUrl(user.getPhotoUrl())
                                .build()
                )
                .build();
    }

    // =========================
    // GENERATE REFRESH TOKEN
    // =========================
    private String generateRefreshToken(User user) {

        refreshTokenRepository.deleteByUserId(user.getId());

        RefreshToken token = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiresAt(LocalDateTime.now().plusDays(30))
                .build();

        return refreshTokenRepository.save(token).getToken();
    }
}