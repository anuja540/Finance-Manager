package com.finance.manager.repository;

import com.finance.manager.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Optional<Budget> findByUserAndCategoryAndMonthAndYear(
            User user, Category category, int month, int year);

    @Query("""
                SELECT COALESCE(SUM(t.amount),0)
                FROM Transaction t
                WHERE t.user = :user
                  AND t.category = :category
                  AND t.type = 'EXPENSE'
                  AND MONTH(t.date) = :month
                  AND YEAR(t.date) = :year
            """)
    Double totalSpent(User user, Category category, int month, int year);
}
