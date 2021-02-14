package premium.calculator.service.calculator;

import org.mapstruct.Mapper;
import premium.calculator.contorller.dto.PolicyDto;
import premium.calculator.domain.Policy;

@Mapper(componentModel = "spring")
public interface PolicyMapper {
    Policy fromDto(PolicyDto policyDto);
}