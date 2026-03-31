package com.finance.manager.repository;

import com.finance.manager.model.Category;
import com.finance.manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);
}
