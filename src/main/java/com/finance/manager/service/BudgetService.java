package com.finance.manager.service;

import com.finance.manager.dto.BudgetRequest;
import com.finance.manager.model.*;
import com.finance.manager.repository.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepo;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;

    public BudgetService(BudgetRepository budgetRepo,
            UserRepository userRepo,
            CategoryRepository categoryRepo) {
        this.budgetRepo = budgetRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    public Budget create(BudgetRequest req, String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        Category category = categoryRepo.findById(req.getCategoryId()).orElseThrow();

        Budget budget = new Budget();
        budget.setUser(user);
        budget.setCategory(category);
        budget.setMonthlyLimit(req.getMonthlyLimit());
        budget.setMonth(req.getMonth());
        budget.setYear(req.getYear());

        return budgetRepo.save(budget);
    }

    public Map<String, Object> checkBudget(Long categoryId, int month, int year, String email) {

        User user = userRepo.findByEmail(email).orElseThrow();
        Category category = categoryRepo.findById(categoryId).orElseThrow();

        Budget budget = budgetRepo
                .findByUserAndCategoryAndMonthAndYear(user, category, month, year)
                .orElseThrow(() -> new RuntimeException("Budget not set"));

        double spent = budgetRepo.totalSpent(user, category, month, year);
        boolean exceeded = spent > budget.getMonthlyLimit();

        return Map.of(
                "limit", budget.getMonthlyLimit(),
                "spent", spent,
                "remaining", budget.getMonthlyLimit() - spent,
                "exceeded", exceeded,
                "alert", exceeded ? "⚠ Budget limit exceeded!" : "Within budget");
    }
}
