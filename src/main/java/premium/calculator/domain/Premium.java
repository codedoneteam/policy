package premium.calculator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Premium {
    private BigDecimal amount;
}
