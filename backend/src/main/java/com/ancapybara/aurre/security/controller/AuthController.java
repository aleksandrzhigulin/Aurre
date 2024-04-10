package com.ancapybara.aurre.security.controller;

import com.ancapybara.aurre.domain.dto.auth.AuthResponseDTO;
import com.ancapybara.aurre.security.service.AuthService;
import com.ancapybara.aurre.domain.dto.auth.AuthRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class AuthController {
    private final AuthService authService;

    @Autowired
    private AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponseDTO> auth(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            AuthResponseDTO response = authService.login(authRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AuthResponseDTO("Invalid password or username", null),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("auth/signup")
    public ResponseEntity<?> signup(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            AuthResponseDTO response = authService.register(authRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("User already exists", HttpStatus.UNAUTHORIZED);
        }
    }


}
