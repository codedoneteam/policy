package premium.calculator.service.calculator.mapper;

import org.mapstruct.Mapper;
import premium.calculator.domain.Premium;
import premium.calculator.integration.dto.PremiumDto;

@Mapper(componentModel = "spring")
public interface PremiumMapper {
    PremiumDto toDto(Premium premium);
}