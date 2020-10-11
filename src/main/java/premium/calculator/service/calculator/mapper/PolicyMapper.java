package premium.calculator.service.calculator.mapper;

import org.mapstruct.Mapper;
import premium.calculator.domain.Policy;
import premium.calculator.integration.dto.PolicyDto;

@Mapper(componentModel = "spring")
public interface PolicyMapper {
    Policy fromDto(PolicyDto policyDto);
}