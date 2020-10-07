package premium.calculator.service.calculator.mapper;

import org.mapstruct.Mapper;
import premium.calculator.controller.dto.PremiumDto;
import premium.calculator.domain.Premium;

@Mapper(componentModel = "spring")
public interface PremiumMapper {
    PremiumDto toDto(Premium premium);
}