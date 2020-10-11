package premium.calculator.service.calculator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import premium.calculator.domain.Premium;
import premium.calculator.exception.PolicyNullException;
import premium.calculator.integration.calculator.PremiumCalculator;
import premium.calculator.integration.dto.PolicyDto;
import premium.calculator.integration.dto.PremiumDto;
import premium.calculator.service.calculator.mapper.PolicyMapper;
import premium.calculator.service.calculator.mapper.PremiumMapper;
import premium.calculator.service.calculator.strategy.PremiumStrategy;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class PremiumCalculatorImpl implements PremiumCalculator {

    private final PolicyMapper policyMapper;

    private final PremiumMapper premiumMapper;

    private final List<PremiumStrategy> strategies;

    @Override
    public PremiumDto calculate(PolicyDto policyDto) {
        if (policyDto == null) throw new PolicyNullException();
        var policy = policyMapper.fromDto(policyDto);
        var amount = strategies.stream()
                    .map(premiumStrategy -> premiumStrategy.execute(policy))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        return premiumMapper.toDto(new Premium(amount));
    }
}
