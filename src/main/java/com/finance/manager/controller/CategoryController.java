package com.finance.manager.controller;

import com.finance.manager.model.Category;
import com.finance.manager.service.CategoryService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public Category create(@RequestBody Category category,
            @AuthenticationPrincipal String email) {
        return service.create(category, email);
    }

    @GetMapping
    public List<Category> getAll(@AuthenticationPrincipal String email) {
        return service.getAll(email);
    }
}
