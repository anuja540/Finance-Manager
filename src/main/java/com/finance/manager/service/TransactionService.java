package com.finance.manager.service;

import com.finance.manager.dto.TransactionRequest;
import com.finance.manager.model.*;
import com.finance.manager.repository.*;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository repo;
    private final UserRepository userRepo;
    private final CategoryRepository catRepo;

    public TransactionService(TransactionRepository repo,
            UserRepository userRepo,
            CategoryRepository catRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.catRepo = catRepo;
    }

    public Transaction add(TransactionRequest req, String email) {
        User user = userRepo.findByEmail(email).orElseThrow();

        Transaction tx = new Transaction();
        tx.setAmount(req.getAmount());
        tx.setType(req.getType());
        tx.setDate(req.getDate());
        tx.setNote(req.getNote());
        tx.setUser(user);
        tx.setCategory(catRepo.findById(req.getCategoryId()).orElseThrow());

        return repo.save(tx);
    }
}
