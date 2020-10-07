package premium.calculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import premium.calculator.controller.calculator.PremiumCalculator;
import premium.calculator.controller.dto.PolicyDto;
import premium.calculator.controller.dto.PremiumDto;

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
