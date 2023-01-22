package pl.alex.cars.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import pl.alex.cars.car.model.ModelResponse;

@WebMvcTest
class ModelControllerTest extends WebTestUtil {


	@DisplayName("[/models/{brand}] - will return 1 model dto matched by brand name")
	@Test
	void testGetModelByBrandName() throws Exception {

		// given
		ModelResponse response = new ModelResponse();
		response.setName(TOYOTA);
		List<ModelResponse> dtos = List.of(response);
		// when
		BDDMockito.given(modelService.getModelResponseByBrandName(ArgumentMatchers.any(String.class)))
				.willReturn(new PageImpl<>(dtos));

		// then
		mockMvc.perform(
				post("/models/{brandName}", TOYOTA)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").isArray())
				.andExpect(jsonPath("$.numberOfElements").value(1));
	}
	
}
