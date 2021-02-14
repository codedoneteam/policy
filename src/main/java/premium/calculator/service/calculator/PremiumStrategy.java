package premium.calculator.service.calculator;

import premium.calculator.domain.Policy;

import java.math.BigDecimal;

/*
    The PremiumStrategy interface is in this package due dependency inversion principle.
    The higher package premium.calculator.service.calculator shouldn't know about lower package premium.calculator.service.calculator.strategy.

    P.S. The comments in the code are used solely to explain how the work is done.
*/

public interface PremiumStrategy {
    BigDecimal execute(Policy policy);
}