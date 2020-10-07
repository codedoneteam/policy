package premium.calculator.service.strategy;

import premium.calculator.domain.Policy;
import premium.calculator.domain.PolicySubObject;
import premium.calculator.domain.RiskType;
import premium.calculator.exception.PolicyNullException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    The method group has been extracted to a separate interface because it maybe would need new risk strategies.

    P.S. The comments in the code are used solely to explain how the work is done.
*/

interface GroupAware {

    default Map<RiskType, List<PolicySubObject>> group(Policy policy) {
        if (policy == null) throw new PolicyNullException();
        return policy.getObjects().stream()
                .flatMap(policyObject -> policyObject.getSubObjects().stream())
                .collect(Collectors.groupingBy(PolicySubObject::getRiskType));
    }

}
