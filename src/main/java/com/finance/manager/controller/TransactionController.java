package com.finance.manager.controller;

import com.finance.manager.dto.TransactionRequest;
import com.finance.manager.model.Transaction;
import com.finance.manager.service.TransactionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public Transaction add(@RequestBody TransactionRequest req,
            @AuthenticationPrincipal String email) {
        return service.add(req, email);
    }
}
