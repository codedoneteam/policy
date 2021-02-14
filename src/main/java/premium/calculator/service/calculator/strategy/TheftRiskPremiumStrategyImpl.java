package premium.calculator.service.calculator.strategy;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import premium.calculator.domain.Policy;
import premium.calculator.exception.PolicyNullException;
import premium.calculator.service.calculator.PremiumStrategy;
import premium.calculator.service.calculator.strategy.common.GroupAware;
import premium.calculator.service.calculator.strategy.common.Summarize;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static premium.calculator.domain.RiskType.THEFT;

@Component
public class TheftRiskPremiumStrategyImpl implements PremiumStrategy, GroupAware, Summarize {

    private final BigDecimal defaultCoefficient;

    private final BigDecimal threshold;

    private final BigDecimal coefficientAfterThreshold;

    public TheftRiskPremiumStrategyImpl(@Value("${app.theft.coefficient.default}") BigDecimal defaultCoefficient,
                                        @Value("${app.theft.threshold}") BigDecimal threshold,
                                        @Value("${app.theft.coefficient.coefficientAfterThreshold}") BigDecimal coefficientAfterThreshold) {
        this.defaultCoefficient = defaultCoefficient;
        this.threshold = threshold;
        this.coefficientAfterThreshold = coefficientAfterThreshold;
    }

    @Override
    public BigDecimal execute(Policy policy) {
        if (policy == null) throw new PolicyNullException();
        var subObjects = group(policy).get(THEFT);
        var sum = summarizeCoveredSum(subObjects);
        return sum.compareTo(threshold) >= 0 ?
                sum.multiply(coefficientAfterThreshold).setScale(2, RoundingMode.HALF_UP) :
                sum.multiply(defaultCoefficient).setScale(2, RoundingMode.HALF_UP);
    }
}