package premium.calculator.service.strategy;

import premium.calculator.domain.PolicySubObject;
import premium.calculator.exception.CoveredSumException;

import java.math.BigDecimal;
import java.util.List;

/*
    The method summarizeCoveredSum has been extracted to a separate interface because it maybe would need new risk strategies.

    P.S. The comments in the code are used solely to explain how the work is done.
*/

interface Summarize {

    default BigDecimal summarizeCoveredSum(List<PolicySubObject> subObjects) {
        return subObjects.stream()
                .map(PolicySubObject::getCoveredSum)
                .filter(this::checkNotNull)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private boolean checkNotNull(BigDecimal coveredSum) {
        if (coveredSum != null) {
            return true;
        } else  {
            throw new CoveredSumException();
        }
    }
}