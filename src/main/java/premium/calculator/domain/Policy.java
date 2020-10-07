package premium.calculator.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Policy {

    @EqualsAndHashCode.Include
    private String number;

    private PolicyStatus status;

    private List<PolicyObject> objects = new ArrayList<>();
}
