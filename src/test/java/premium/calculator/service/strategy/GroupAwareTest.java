package premium.calculator.service.strategy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import premium.calculator.domain.Policy;

import static java.math.BigDecimal.ONE;
import static premium.calculator.domain.RiskType.FIRE;
import static premium.calculator.domain.RiskType.THEFT;

class GroupAwareTest implements GroupAware, PolicyCreator {

    @Test
    @DisplayName("Test empty structure")
    void emptyTest() {
        Policy policy = policy();
        Assertions.assertEquals(0, group(policy).size());
    }

    @Test
    @DisplayName("Test different risk types")
    void testDifferentTest() {
        var policy = policy(object(subObject(FIRE, ONE),subObject(THEFT, ONE)));
        Assertions.assertEquals(2, group(policy).size());
    }

    @Test
    @DisplayName("Test same risk types")
    void testSameRiskTypes() {
        var policy = policy(object(subObject(FIRE, ONE), subObject(FIRE, ONE)));
        Assertions.assertEquals(1, group(policy).size());
    }

    @Test
    @DisplayName("Test different objects")
    void testDifferentObjects() {
        var policy = policy(object(subObject(FIRE, ONE)), object(subObject(FIRE, ONE)));
        Assertions.assertEquals(1, group(policy).size());
    }
}
