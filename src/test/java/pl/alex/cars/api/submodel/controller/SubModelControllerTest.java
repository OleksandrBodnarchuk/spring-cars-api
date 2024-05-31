package pl.alex.cars.api.submodel.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;

import pl.alex.cars.api.WebTestUtil;
import pl.alex.cars.api.modification.dto.ModificationResponse;

@WebMvcTest
class SubModelControllerTest extends WebTestUtil {


	@DisplayName("[/submodels/{model}] - will return 1 model dto matched by brand name")
	@Test
	void testGetModelByBrandName() throws Exception {

		// given
		ModificationResponse response = new ModificationResponse();
		String submodel = "A3 Hatchback (8V)";
		response.setName(submodel);
		List<ModificationResponse> dtos = List.of(response);
		// when
		BDDMockito.given(modificationService.getSubModelResponseByBrandName(ArgumentMatchers.any(String.class)))
				.willReturn(new PageImpl<>(dtos));

		// then
		mockMvc.perform(
				get("/v0/submodels/{model}", AUDI)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").isArray())
				.andExpect(jsonPath("$.content.[0].name").value(submodel))
				.andExpect(jsonPath("$.numberOfElements").value(1));
	}

}
