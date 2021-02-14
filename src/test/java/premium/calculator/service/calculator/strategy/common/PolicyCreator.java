package premium.calculator.service.calculator.strategy.common;

import premium.calculator.domain.Policy;
import premium.calculator.domain.PolicyObject;
import premium.calculator.domain.PolicySubObject;
import premium.calculator.domain.RiskType;

import java.math.BigDecimal;
import java.util.Arrays;

interface PolicyCreator {

    String POLICE_NUMBER = "00001";

    default Policy policy(PolicyObject... policyObjects) {
        var policy = new Policy();
        policy.setNumber(POLICE_NUMBER);
        policy.setObjects(Arrays.asList(policyObjects));;
        return policy;
    }

    default PolicyObject object(PolicySubObject... policySubObjects) {
        var policyObject = new PolicyObject();
        policyObject.setSubObjects(Arrays.asList(policySubObjects));
        return policyObject;
    }

    default PolicySubObject subObject(RiskType riskType, BigDecimal coveredSum) {
        var subObject = new PolicySubObject();
        subObject.setRiskType(riskType);
        subObject.setCoveredSum(coveredSum);
        return subObject;
    }
}