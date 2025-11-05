package com.wellness.crud.wellness.controller;

import com.wellness.crud.wellness.dto.AuthRequest;
import com.wellness.crud.wellness.dto.AuthResponse;
import com.wellness.crud.wellness.dto.RegisterRequest;
import com.wellness.crud.wellness.services.AuthenticationService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            String token = authenticationService.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request, BindingResult result) {
        logger.info("→ Recibido registro para: {}", request.getEmail());

        if (result.hasErrors()) {
            String errores = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            logger.warn("Errores de validación: {}", errores);
            return ResponseEntity.badRequest().body("Errores de validación: " + errores);
        }

        try {
            authenticationService.register(request);
            logger.info("Registro completado para: {}", request.getEmail());
            return ResponseEntity.ok("Registro exitoso");
        } catch (Exception e) {
            logger.error("Fallo en el registro para {}: {}", request.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}
