package premium.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import premium.calculator.StartTest;
import premium.calculator.controller.calculator.PremiumCalculator;
import premium.calculator.controller.dto.PolicyDto;
import premium.calculator.controller.dto.PremiumDto;

import static java.math.BigDecimal.TEN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@StartTest
class PremiumControllerTest {

    private static final String NUMBER = "00001";
    private static final String URL = "/api/calculated";
    public static final String CONTENT_TYPE = "application/json";

    @MockBean
    private PremiumCalculator service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Premium controller test")
    @Test
    void premiumControllerTest() throws Exception {
        Mockito.when(service.calculate(Mockito.any())).thenReturn(new PremiumDto(TEN));

        var policyDto = new PolicyDto();
        policyDto.setNumber(NUMBER);

        var result = objectMapper.readValue(mockMvc.perform(post(URL)
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(policyDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString(), PremiumDto.class);

        Assertions.assertEquals(0, result.getAmount().compareTo(TEN));
    }

}
