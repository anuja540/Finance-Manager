package com.finance.manager.controller;

import com.finance.manager.dto.*;
import com.finance.manager.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {
        service.register(req);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        return service.login(req);
    }
}
