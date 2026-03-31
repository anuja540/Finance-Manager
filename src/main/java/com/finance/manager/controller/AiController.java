package com.finance.manager.controller;

import com.finance.manager.dto.AiChatRequest;
import com.finance.manager.service.AiService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService service;

    public AiController(AiService service) {
        this.service = service;
    }

    @PostMapping("/chat")
    public Map<String, Object> chat(@RequestBody AiChatRequest req,
            @AuthenticationPrincipal String email) {
        return service.advise(email, req.getQuestion(), req.getMonth(), req.getYear());
    }
}