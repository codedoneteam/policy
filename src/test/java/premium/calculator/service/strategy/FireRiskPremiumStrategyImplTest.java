package premium.calculator.service.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import premium.calculator.service.calculator.strategy.PremiumStrategy;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static premium.calculator.domain.RiskType.FIRE;

class FireRiskPremiumStrategyImplTest implements PolicyCreator {

    private PremiumStrategy premiumStrategy = new FireRiskPremiumStrategyImpl(valueOf(0.014), valueOf(100), valueOf(0.024));

    @DisplayName("Test FIRE calculation strategy before threshold")
    @Test
    void testBeforeThreshold() {
        var policy = policy(object(subObject(FIRE, TEN)));
        assertEquals(0, premiumStrategy.execute(policy).compareTo(valueOf(0.14)));
    }

    @DisplayName("Test FIRE calculation strategy on threshold")
    @Test
    void testEqualsThreshold() {
        var policy = policy(object(subObject(FIRE, valueOf(100))));
        assertEquals(0, premiumStrategy.execute(policy).compareTo(valueOf(1.40)));
    }

    @DisplayName("Test FIRE calculation strategy after threshold")
    @Test
    void testAfterThreshold() {
        var policy = policy(object(subObject(FIRE, valueOf(1000))));
        assertEquals(0, premiumStrategy.execute(policy).compareTo(valueOf(24)));
    }
}