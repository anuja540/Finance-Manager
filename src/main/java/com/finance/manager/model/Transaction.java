package com.finance.manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // INCOME / EXPENSE
    private Double amount;
    private LocalDate date;
    private String note;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
