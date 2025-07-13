package com.mutualfunds.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // BUY or SELL

    private Long userId;

    private Long mutualFundId;

    private BigDecimal units;

    private BigDecimal amount;

    private LocalDate transactionDate;
}
