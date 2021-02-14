package premium.calculator.service.calculator;

import org.mapstruct.Mapper;
import premium.calculator.contorller.dto.PremiumDto;
import premium.calculator.domain.Premium;

@Mapper(componentModel = "spring")
public interface PremiumMapper {
    PremiumDto toDto(Premium premium);
}