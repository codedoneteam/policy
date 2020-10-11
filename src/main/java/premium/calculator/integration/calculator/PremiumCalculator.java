package premium.calculator.integration.calculator;

import premium.calculator.integration.dto.PolicyDto;
import premium.calculator.integration.dto.PremiumDto;

/*
    The PremiumCalculator interface is in this package due dependency inversion principle.
    The higher package  premium.calculator.controller shouldn't know about lower package premium.calculator.service.calculator.

    P.S. The comments in the code are used solely to explain how the work is done.
*/

public interface PremiumCalculator {
    PremiumDto calculate(PolicyDto policyDto);
}
