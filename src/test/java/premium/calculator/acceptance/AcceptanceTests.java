package premium.calculator.acceptance;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import premium.calculator.StartTest;
import premium.calculator.controller.dto.PolicyDto;
import premium.calculator.controller.dto.PolicyObjectDto;
import premium.calculator.controller.dto.PolicySubObjectDto;
import premium.calculator.controller.dto.PremiumDto;

import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static premium.calculator.controller.dto.RiskTypeDto.FIRE;
import static premium.calculator.controller.dto.RiskTypeDto.THEFT;

@StartTest
class AcceptanceTests {

    private static final String URL = "/api/calculated";
    private static final String CONTENT_TYPE = "application/json";
    private static final String POLICY_NUMBER = "000001";

    @Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@DisplayName("Acceptance test Nr 1")
	@Test
	void acceptanceTest1() throws Exception {
		var policyDto = new PolicyDto();
		policyDto.setNumber(POLICY_NUMBER);

		var policyObjectDto = new PolicyObjectDto();
		policyDto.setObjects(List.of(policyObjectDto));

		var policySubObjectDto1 = new PolicySubObjectDto();
		policyObjectDto.getSubObjects().add(policySubObjectDto1);
		policySubObjectDto1.setRiskType(FIRE);
		policySubObjectDto1.setCoveredSum(valueOf(100));


		var policySubObjectDto2 = new PolicySubObjectDto();
		policyObjectDto.getSubObjects().add(policySubObjectDto2);
		policySubObjectDto2.setRiskType(THEFT);
		policySubObjectDto2.setCoveredSum(valueOf(8));


		var result = objectMapper.readValue(mockMvc.perform(post(URL)
				.contentType(CONTENT_TYPE)
				.content(objectMapper.writeValueAsString(policyDto)))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse()
				.getContentAsString(), PremiumDto.class);

		Assertions.assertEquals(0, result.getAmount().compareTo(valueOf(2.28)));
	}

    @DisplayName("Acceptance test Nr 1")
	@Test
	void acceptanceTest2() throws Exception {
		var policyDto = new PolicyDto();
		policyDto.setNumber(POLICY_NUMBER);

		var policyObjectDto = new PolicyObjectDto();
		policyDto.setObjects(List.of(policyObjectDto));

		var policySubObject1 = new PolicySubObjectDto();
		policyObjectDto.getSubObjects().add(policySubObject1);
		policySubObject1.setRiskType(FIRE);
		policySubObject1.setCoveredSum(valueOf(500));


		var policySubObject2 = new PolicySubObjectDto();
		policyObjectDto.getSubObjects().add(policySubObject2);
		policySubObject2.setRiskType(THEFT);
		policySubObject2.setCoveredSum(valueOf(102.51));


		var result = objectMapper.readValue(mockMvc.perform(post(URL)
				.contentType(CONTENT_TYPE)
				.content(objectMapper.writeValueAsString(policyDto)))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse()
				.getContentAsString(), PremiumDto.class);

		Assertions.assertEquals(0, result.getAmount().compareTo(valueOf(17.13)));
	}
}
