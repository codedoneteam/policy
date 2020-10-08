package premium.calculator.service.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import premium.calculator.service.calculator.strategy.PremiumStrategy;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static premium.calculator.domain.RiskType.THEFT;

/*
    There are two zones of equivalence: before threshold and after threshold.
    So we should write three tests.
    The first before threshold, the second threshold and the third after threshold.

    P.S. The comments in the code are used solely to explain how the work is done.
*/

class TheftRiskPremiumStrategyImplTest implements PolicyCreator {

    private PremiumStrategy premiumStrategy = new TheftRiskPremiumStrategyImpl(valueOf(0.11), valueOf(15), valueOf(0.05));

    @DisplayName("Test THEFT calculation strategy before threshold")
    @Test
    void testBeforeThreshold() {
        var policy = policy(object(subObject(THEFT, TEN)));
        assertEquals(0, premiumStrategy.execute(policy).compareTo(valueOf(1.10)));
    }

    @DisplayName("Test THEFT calculation strategy before threshold")
    @Test
    void testEqualsThreshold() {
        var policy = policy(object(subObject(THEFT, valueOf(15))));
        assertEquals(0, premiumStrategy.execute(policy).compareTo(valueOf(0.75)));
    }

    @DisplayName("Test THEFT calculation strategy before threshold")
    @Test
    void testAfterThreshold() {
        var policy = policy(object(subObject(THEFT, valueOf(1000))));
        assertEquals(0, premiumStrategy.execute(policy).compareTo(valueOf(50)));
    }

}