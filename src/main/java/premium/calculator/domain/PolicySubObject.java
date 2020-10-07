package premium.calculator.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PolicySubObject {
    private String name;

    private BigDecimal coveredSum;

    private RiskType riskType;
}