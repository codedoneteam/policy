package premium.calculator.integration.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PolicyObjectDto {
    private String name;

    private List<PolicySubObjectDto> subObjects = new ArrayList<>();
}