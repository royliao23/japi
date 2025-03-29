package com.example.controller;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.Map;

import com.example.model.AuthUser;
import com.example.repository.AuthUserRepository;
import com.example.service.CustomUserDetailsService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthController(
            AuthUserRepository authUserRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthUser authUser) {
        if (authUserRepository.existsByUsername(authUser.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        
        if (authUserRepository.existsByEmail(authUser.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        
        AuthUser newUser = new AuthUser();
        newUser.setUsername(authUser.getUsername());
        newUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        newUser.setEmail(authUser.getEmail());
        newUser.setFirstName(authUser.getFirstName());
        newUser.setLastName(authUser.getLastName());
        newUser.setIsActive(true);
        newUser.setIsStaff(false);
        newUser.setIsSuperuser(false);
        newUser.setDateJoined(LocalDateTime.now());
        
        authUserRepository.save(newUser);
        
        return ResponseEntity.ok(
            Map.of("message", "User registered successfully", "username", newUser.getUsername())
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, 
                                 @RequestParam String password) {
        System.out.println("Hello World!");
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(Map.of("message", "Login successful"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}