package com.finance.manager.service;

import com.finance.manager.model.User;
import com.finance.manager.repository.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DashboardService {

    private final UserRepository userRepo;
    private final TransactionRepository txRepo;

    public DashboardService(UserRepository userRepo, TransactionRepository txRepo) {
        this.userRepo = userRepo;
        this.txRepo = txRepo;
    }

    public Map<String, Double> summary(String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        double income = txRepo.totalIncome(user);
        double expense = txRepo.totalExpense(user);

        return Map.of(
                "totalIncome", income,
                "totalExpense", expense,
                "balance", income - expense);
    }
}
