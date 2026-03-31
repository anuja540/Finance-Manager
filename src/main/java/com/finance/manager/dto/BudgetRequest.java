package com.finance.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BudgetRequest {
    private Long categoryId;
    private Double monthlyLimit;
    private int month;
    private int year;
}
