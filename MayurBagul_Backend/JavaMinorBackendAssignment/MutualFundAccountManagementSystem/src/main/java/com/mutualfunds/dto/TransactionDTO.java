package com.mutualfunds.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO {

    @NotNull(message = "Mutual fund ID is required")
    private Long mutualFundId;

    @NotNull(message = "Units must be provided")
    @DecimalMin(value = "0.1", message = "Units must be at least 0.1")
    private BigDecimal units;
}
