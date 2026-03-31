package com.finance.manager.service;

import com.finance.manager.model.User;
import com.finance.manager.repository.TransactionRepository;
import com.finance.manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService {

    private final UserRepository userRepo;
    private final TransactionRepository txRepo;

    public ReportService(UserRepository userRepo, TransactionRepository txRepo) {
        this.userRepo = userRepo;
        this.txRepo = txRepo;
    }

    // Bar chart data: [{month, income, expense}]
    public List<Map<String, Object>> incomeVsExpenseByYear(int year, String email) {
        User user = userRepo.findByEmail(email).orElseThrow();

        List<Object[]> rows = txRepo.incomeExpenseByMonth(user, year);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] r : rows) {
            result.add(Map.of(
                    "month", r[0],
                    "income", r[1],
                    "expense", r[2]));
        }
        return result;
    }

    // Pie chart data: [{category, total}]
    public List<Map<String, Object>> expenseByCategory(int month, int year, String email) {
        User user = userRepo.findByEmail(email).orElseThrow();

        List<Object[]> rows = txRepo.expenseByCategory(user, month, year);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] r : rows) {
            result.add(Map.of(
                    "category", r[0],
                    "total", r[1]));
        }
        return result;
    }

    // Daily totals: [{day, total}]
    public List<Map<String, Object>> dailyExpenses(int month, int year, String email) {
        User user = userRepo.findByEmail(email).orElseThrow();

        List<Object[]> rows = txRepo.dailyExpenseTotals(user, month, year);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] r : rows) {
            result.add(Map.of(
                    "day", r[0],
                    "total", r[1]));
        }
        return result;
    }
}