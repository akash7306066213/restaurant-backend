package com.restaurant.service;

import com.restaurant.dto.LoginRequest;
import com.restaurant.dto.RegisterRequest;
import com.restaurant.model.Role;
import com.restaurant.model.User;
import com.restaurant.repository.UserRepository;
import com.restaurant.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // REGISTER
    public User register(RegisterRequest req) {

        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User u = new User();
        u.setName(req.getName());
        u.setEmail(req.getEmail());
        u.setPassword(passwordEncoder.encode(req.getPassword()));

        // default role
        if(req.getRole()==null || req.getRole().isBlank()){
            u.setRole(Role.ROLE_CUSTOMER);
        } else {
            u.setRole(Role.valueOf(req.getRole()));
        }

        return userRepository.save(u);
    }

    // LOGIN
    public Map<String, Object> login(LoginRequest req){

        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if(!passwordEncoder.matches(req.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), Map.of(
                "id", user.getId(),
                "name", user.getName(),
                "role", user.getRole().name()
        ));

        Map<String,Object> response = new HashMap<>();
        response.put("token", token);
        response.put("id", user.getId());
        response.put("name", user.getName());
        response.put("email", user.getEmail());
        response.put("role", user.getRole().name());

        return response;
    }
}
