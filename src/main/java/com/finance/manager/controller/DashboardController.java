package com.finance.manager.controller;

import com.finance.manager.service.DashboardService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/summary")
    public Map<String, Double> summary(@AuthenticationPrincipal String email) {
        return service.summary(email);
    }
}
