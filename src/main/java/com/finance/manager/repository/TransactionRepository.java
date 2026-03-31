package com.finance.manager.repository;

import com.finance.manager.model.Transaction;
import com.finance.manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT COALESCE(SUM(t.amount),0) FROM Transaction t WHERE t.user = :user AND t.type='INCOME'")
    Double totalIncome(User user);

    @Query("SELECT COALESCE(SUM(t.amount),0) FROM Transaction t WHERE t.user = :user AND t.type='EXPENSE'")
    Double totalExpense(User user);

    // ✅ Monthly Income vs Expense (bar chart)
    @Query("""
                SELECT MONTH(t.date),
                       COALESCE(SUM(CASE WHEN t.type='INCOME' THEN t.amount ELSE 0 END),0),
                       COALESCE(SUM(CASE WHEN t.type='EXPENSE' THEN t.amount ELSE 0 END),0)
                FROM Transaction t
                WHERE t.user = :user AND YEAR(t.date) = :year
                GROUP BY MONTH(t.date)
                ORDER BY MONTH(t.date)
            """)
    List<Object[]> incomeExpenseByMonth(User user, int year);

    // ✅ Expense by Category (pie chart) for a month
    @Query("""
                SELECT t.category.name, COALESCE(SUM(t.amount),0)
                FROM Transaction t
                WHERE t.user = :user
                  AND t.type='EXPENSE'
                  AND YEAR(t.date)=:year AND MONTH(t.date)=:month
                GROUP BY t.category.name
                ORDER BY SUM(t.amount) DESC
            """)
    List<Object[]> expenseByCategory(User user, int month, int year);

    // ✅ Daily expense totals for a month (calendar/trend)
    @Query("""
                SELECT DAY(t.date), COALESCE(SUM(t.amount),0)
                FROM Transaction t
                WHERE t.user = :user
                  AND t.type='EXPENSE'
                  AND YEAR(t.date)=:year AND MONTH(t.date)=:month
                GROUP BY DAY(t.date)
                ORDER BY DAY(t.date)
            """)
    List<Object[]> dailyExpenseTotals(User user, int month, int year);
}