package com.restaurant.controller;

import com.restaurant.dto.LoginRequest;
import com.restaurant.dto.RegisterRequest;
import com.restaurant.model.User;
import com.restaurant.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")  // <-- allows react / postman to hit API
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        User saved = authService.register(req);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }
    
    @GetMapping("/secured")
    public String securedHello(){
        return "This is secured API. Token verified!";
    }
}
