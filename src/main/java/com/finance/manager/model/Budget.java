package com.finance.manager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double monthlyLimit;
    private int month;
    private int year;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
