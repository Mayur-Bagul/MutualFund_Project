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
public class MutualFund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fundName;

    @Column(unique = true)
    private String fundCode; // e.g., ICICI001

    private String category; // Equity, Debt, Hybrid

    private BigDecimal nav; // Net Asset Value

    private LocalDate lastUpdated;
}
