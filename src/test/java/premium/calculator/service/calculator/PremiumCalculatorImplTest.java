package premium.calculator.service.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import premium.calculator.contorller.dto.PolicyDto;
import premium.calculator.contorller.dto.PolicyObjectDto;
import premium.calculator.contorller.dto.PolicySubObjectDto;
import premium.calculator.service.calculator.mapper.PolicyMapperImpl;

import static java.math.BigDecimal.*;
import static java.util.List.of;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PremiumCalculatorImplTest {

    private static final String NUMBER = "00001";

    @Mock
    private PremiumStrategy premiumStrategy;

    @Mock
    private PremiumStrategy premiumStrategy2;

    @DisplayName("Premium calculator test")
    @Test
    void premiumCalculatorTest() {
        Mockito.lenient().when(premiumStrategy.execute(any())).thenReturn(ONE);
        Mockito.lenient().when(premiumStrategy2.execute(any())).thenReturn(TEN);


        var policyDto = new PolicyDto();
        policyDto.setNumber(NUMBER);

        var policyObjectDto = new PolicyObjectDto();
        policyDto.getObjects().add(policyObjectDto);

        var policySubObjectDto1 = new PolicySubObjectDto();
        policySubObjectDto1.setCoveredSum(ONE);

        var policySubObjectDto2 = new PolicySubObjectDto();
        policySubObjectDto1.setCoveredSum(TEN);

        policyObjectDto.getSubObjects().add(policySubObjectDto1);
        policyObjectDto.getSubObjects().add(policySubObjectDto2);

        var premiumCalculator = new PremiumCalculatorImpl(new PolicyMapperImpl(),
                                                          new PremiumMapperImpl(),
                                                          of(premiumStrategy, premiumStrategy2));

        Assertions.assertEquals(0, premiumCalculator.calculate(policyDto).getAmount().compareTo(valueOf(11)));
    }

}