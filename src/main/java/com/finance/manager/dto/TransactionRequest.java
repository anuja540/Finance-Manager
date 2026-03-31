package com.finance.manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TransactionRequest {
    private String type;
    private Double amount;
    private LocalDate date;
    private String note;
    private Long categoryId;
}
