package com.finance.manager.controller;

import com.finance.manager.service.ReportService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    // ✅ Bar chart: monthly income vs expense
    @GetMapping("/income-expense")
    public List<Map<String, Object>> incomeExpense(@RequestParam int year,
            @AuthenticationPrincipal String email) {
        return service.incomeVsExpenseByYear(year, email);
    }

    // ✅ Pie chart: expense by category
    @GetMapping("/expense-by-category")
    public List<Map<String, Object>> expenseByCategory(@RequestParam int month,
            @RequestParam int year,
            @AuthenticationPrincipal String email) {
        return service.expenseByCategory(month, year, email);
    }

    // ✅ Daily totals for trend/calendar
    @GetMapping("/daily-expenses")
    public List<Map<String, Object>> daily(@RequestParam int month,
            @RequestParam int year,
            @AuthenticationPrincipal String email) {
        return service.dailyExpenses(month, year, email);
    }
}