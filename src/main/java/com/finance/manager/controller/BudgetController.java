package com.finance.manager.controller;

import com.finance.manager.dto.BudgetRequest;
import com.finance.manager.model.Budget;
import com.finance.manager.service.BudgetService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService service;

    public BudgetController(BudgetService service) {
        this.service = service;
    }

    @PostMapping
    public Budget create(@RequestBody BudgetRequest req,
            @AuthenticationPrincipal String email) {
        return service.create(req, email);
    }

    @GetMapping("/check")
    public Map<String, Object> check(@RequestParam Long categoryId,
            @RequestParam int month,
            @RequestParam int year,
            @AuthenticationPrincipal String email) {
        return service.checkBudget(categoryId, month, year, email);
    }
}
