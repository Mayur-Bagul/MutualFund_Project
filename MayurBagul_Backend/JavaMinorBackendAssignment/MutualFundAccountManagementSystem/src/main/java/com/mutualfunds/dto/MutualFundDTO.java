package com.mutualfunds.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MutualFundDTO {

    @NotBlank(message = "Fund name is required")
    private String fundName;

    @NotBlank(message = "Fund code is required")
    private String fundCode;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "NAV must be provided")
    @DecimalMin(value = "1.0", message = "NAV must be at least 1.0")
    private BigDecimal nav;
}
