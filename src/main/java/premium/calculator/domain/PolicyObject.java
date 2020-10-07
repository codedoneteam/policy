package premium.calculator.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PolicyObject {
    private String name;

    private List<PolicySubObject> subObjects = new ArrayList<>();
}