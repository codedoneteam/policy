package premium.calculator.integration.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class PolicySubObjectDto {
    private String name;

    @NotBlank(message = "Covered sum is mandatory")
    private BigDecimal coveredSum;

    private RiskTypeDto riskType;
}