package com.finance.manager.service;

import com.finance.manager.dto.*;
import com.finance.manager.model.User;
import com.finance.manager.repository.UserRepository;
import com.finance.manager.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository repo, BCryptPasswordEncoder encoder, JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    public void register(RegisterRequest req) {
        User user = new User(null, req.getName(), req.getEmail(),
                encoder.encode(req.getPassword()));
        repo.save(user);
    }

    public String login(LoginRequest req) {
        User user = repo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(req.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        return jwtUtil.generateToken(user.getEmail());
    }
}
