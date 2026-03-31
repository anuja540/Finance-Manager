package com.finance.manager.service;

import com.finance.manager.model.User;
import com.finance.manager.repository.BudgetRepository;
import com.finance.manager.repository.TransactionRepository;
import com.finance.manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AiService {

    private final UserRepository userRepo;
    private final TransactionRepository txRepo;
    private final BudgetRepository budgetRepo;

    public AiService(UserRepository userRepo, TransactionRepository txRepo, BudgetRepository budgetRepo) {
        this.userRepo = userRepo;
        this.txRepo = txRepo;
        this.budgetRepo = budgetRepo;
    }

    public Map<String, Object> advise(String email, String question, int month, int year) {
        User user = userRepo.findByEmail(email).orElseThrow();

        double totalIncome = txRepo.totalIncome(user);
        double totalExpense = txRepo.totalExpense(user);
        double balance = totalIncome - totalExpense;

        // Simple, helpful rule-based advice (safe default)
        String advice;
        if (totalExpense > totalIncome) {
            advice = "Your expenses are higher than your income. Consider setting strict category budgets (Food/Transport) and reduce non-essential spending.";
        } else if (balance < totalIncome * 0.1) {
            advice = "Your savings are low compared to income. Try the 50/30/20 rule: 50% needs, 30% wants, 20% savings.";
        } else {
            advice = "You are maintaining a positive balance. You can improve by tracking category-wise expenses and setting monthly limits.";
        }

        return Map.of(
                "question", question,
                "summary", Map.of(
                        "totalIncome", totalIncome,
                        "totalExpense", totalExpense,
                        "balance", balance),
                "advice", advice,
                "note", "AI suggestions are informational only, not professional financial advice.");
    }
}