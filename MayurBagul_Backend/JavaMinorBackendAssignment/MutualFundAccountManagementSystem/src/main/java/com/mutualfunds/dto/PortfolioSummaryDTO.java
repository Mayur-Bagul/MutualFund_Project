package com.mutualfunds.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PortfolioSummaryDTO {
    private String fundName;
    private String fundCode;
    private BigDecimal totalUnits;
    private BigDecimal amountInvested;
    private BigDecimal currentNAV;
    private BigDecimal currentValue;
    private BigDecimal profitOrLoss;
}
