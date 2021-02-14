package premium.calculator.service.calculator.strategy.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import premium.calculator.domain.PolicySubObject;

import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SummarizeTest implements Summarize {

    @Test
    @DisplayName("Summarize sub-objects zero count")
    void summarizeZero() {
        assertEquals(0, summarizeCoveredSum(List.of()).compareTo(ZERO));
    }

    @Test
    @DisplayName("Summarize two sub-objects")
    void summarizeTwoFire() {
        var subObjects = new ArrayList<PolicySubObject>();

        var subObject1 = new PolicySubObject();
        subObject1.setCoveredSum(ONE);
        subObjects.add(subObject1);

        var subObject2 = new PolicySubObject();
        subObject2.setCoveredSum(TEN);
        subObjects.add(subObject2);

        assertEquals(0, summarizeCoveredSum(subObjects).compareTo(valueOf(11)));
    }
}