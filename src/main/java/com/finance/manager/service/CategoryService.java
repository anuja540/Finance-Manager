package com.finance.manager.service;

import com.finance.manager.model.Category;
import com.finance.manager.model.User;
import com.finance.manager.repository.CategoryRepository;
import com.finance.manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repo;
    private final UserRepository userRepo;

    public CategoryService(CategoryRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public Category create(Category category, String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        category.setUser(user);
        return repo.save(category);
    }

    public List<Category> getAll(String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        return repo.findByUser(user);
    }
}
