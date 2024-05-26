package pl.alex.cars.api.controller;

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
import pl.alex.cars.car.model.ModelResponse;

@WebMvcTest
class ModelControllerTest extends WebTestUtil {


	@DisplayName("[/models/{brand}] - will return 1 model dto matched by brand name")
	@Test
	void testGetModelByBrandName() throws Exception {

		// given
		ModelResponse avensins = new ModelResponse("Avensins");
		ModelResponse hilux = new ModelResponse("Hilux");
		ModelResponse corolla = new ModelResponse("Corolla");

		List<ModelResponse> dtos = List.of(avensins, hilux, corolla);
		// when
		BDDMockito.given(modelService.getModelResponseByBrandName(ArgumentMatchers.any()))
				.willReturn(new PageImpl<>(dtos));

		// then
		mockMvc.perform(
				get("/models/{brandName}", TOYOTA)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").isArray())
				.andExpect(jsonPath("$.numberOfElements").value(dtos.size()));
	}
	
}
