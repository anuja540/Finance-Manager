package com.finance.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AiChatRequest {
    private String question;
    private int month;
    private int year;
}