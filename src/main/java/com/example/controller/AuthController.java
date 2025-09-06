package com.example.controller;

import com.example.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    // -------- LOGIN --------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

            // Access token: 15 minutes
            String accessToken = jwtUtil.generateToken(userDetails, 15 * 60 * 1000);

            // Refresh token: 7 days
            String refreshToken = jwtUtil.generateToken(userDetails, 7 * 24 * 60 * 60 * 1000);

            Map<String, Object> response = new HashMap<>();
            response.put("token", accessToken);
            response.put("refresh_token", refreshToken);
            response.put("token_type", "bearer");

            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", 1); // TODO: fetch from DB
            userInfo.put("username", userDetails.getUsername());
            userInfo.put("role", "USER"); // TODO: map from authorities
            userInfo.put("email", "email@example.com"); // TODO: fetch from DB
            response.put("user", userInfo);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("detail", "Incorrect username or password"));
        }
    }

    // -------- REFRESH --------
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest request) {
        try {
            String username = jwtUtil.extractUsername(request.getRefresh_token());
            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("detail", "Invalid refresh token"));
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String newAccessToken = jwtUtil.generateToken(userDetails, 15 * 60 * 1000);

            return ResponseEntity.ok(
                    Map.of("access_token", newAccessToken, "token_type", "bearer")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("detail", "Invalid refresh token"));
        }
    }

    // -------- SIGNUP --------
    @PostMapping("/v1/signup")
    public ResponseEntity<?> signup(@RequestBody RegisterRequest user) {
        // TODO: check DB for existing username & save user
        boolean userExists = false;
        if (userExists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("detail", "Username already exists"));
        }

        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    // ---- DTOs ----
    public static class LoginRequest {
        private String username;
        private String password;
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class RefreshTokenRequest {
        private String refresh_token;
        public String getRefresh_token() { return refresh_token; }
        public void setRefresh_token(String refresh_token) { this.refresh_token = refresh_token; }
    }

    public static class RegisterRequest {
        private String username;
        private String password;
        private String email;
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}
