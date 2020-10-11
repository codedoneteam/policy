package premium.calculator.integration.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PolicyDto {

    @NotBlank(message = "Policy number is mandatory")
    @EqualsAndHashCode.Include
    private String number;

    private PolicyStatusDto status;

    private List<PolicyObjectDto> objects = new ArrayList<>();
}
