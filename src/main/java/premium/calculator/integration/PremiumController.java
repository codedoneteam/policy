package premium.calculator.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import premium.calculator.integration.calculator.PremiumCalculator;
import premium.calculator.integration.dto.PolicyDto;
import premium.calculator.integration.dto.PremiumDto;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PremiumController {

    private final PremiumCalculator calculator;

    @PostMapping("api/calculated")
    public PremiumDto calculate(@Valid @RequestBody PolicyDto policyDto) {
        return calculator.calculate(policyDto);
    }

}
