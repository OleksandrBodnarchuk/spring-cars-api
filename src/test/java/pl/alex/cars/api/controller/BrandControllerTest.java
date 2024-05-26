package pl.alex.cars.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;

import pl.alex.cars.car.brand.BrandRequest;
import pl.alex.cars.car.brand.BrandResponse;
import pl.alex.cars.car.model.ModelResponse;

@WebMvcTest
class BrandControllerTest extends WebTestUtil {

	@DisplayName("[/brands/brands] - will return multiple dtos matched by their names")
	@Test
	void test_getMultipleBrands_shouldReturn_dto_list() throws JsonProcessingException, Exception {
		// given
		BrandRequest request = new BrandRequest();
		request.setNames(List.of(BMW, AUDI, TOYOTA));
		List<BrandResponse> brandResponse = new ArrayList<>(); 
		request.getNames().forEach(name -> {
			BrandResponse response = new BrandResponse();
			response.setName(name);
			brandResponse.add(response);
		});
		
		// when
		BDDMockito.given(brandService.getMultipleBrands(ArgumentMatchers.any(BrandRequest.class))).willReturn(brandResponse);
		
		// then
		mockMvc.perform(post("/brands/brands")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$.[0].name").value(BMW));
	}
	
	@DisplayName("[/brands/brands] - will FAIL on validation")
	@Test
	void test_getMultipleBrands_shouldFAIL_onValidation() throws JsonProcessingException, Exception {
		// given
		BrandRequest request = new BrandRequest();
		request.setNames(List.of());
		
		// when
		BDDMockito.given(brandService.getMultipleBrands(ArgumentMatchers.any(BrandRequest.class))).willReturn(List.of());
		
		// then
		mockMvc.perform(post("/brands/brands")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request)))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
	
	@DisplayName("[/brands/{name}] - will return 1 dto matched by name")
	@Test
	void test_getBrandByName_shouldReturn_dto() throws JsonProcessingException, Exception {
		// given
		BrandRequest request = new BrandRequest();
		request.setNames(List.of(BMW));
		BrandResponse brandResponse = new BrandResponse();
		brandResponse.setName(BMW);
		
		// when
		BDDMockito.given(brandService.getBrandResponseByName(request.getNames().get(0)))
						.willReturn(brandResponse);
		
		// then
		mockMvc.perform(post("/brands/{name}", BMW)
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(BMW));
	}
	
	@DisplayName("[/brands] - will return page of dtos")
	@Test
	void test_getBrands_shouldReturn_Pages() throws JsonProcessingException, Exception {
		// given
		BrandRequest request = new BrandRequest();
		List<BrandResponse> dtos = new ArrayList<>();
		BrandResponse dto;
		for (int i = 0; i < 3; i++) {
			dto = new BrandResponse();
			dto.setOrdinal(i);
			dto.setName("DTO: " + i);
			dtos.add(dto);
		}
		
		// when
		BDDMockito.given(brandService.findAllBrands(ArgumentMatchers.any(BrandRequest.class)))
				.willReturn(new PageImpl<>(dtos));
		
		// then
		mockMvc.perform(post("/brands")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.content").isArray())
		.andExpect(jsonPath("$.numberOfElements").value(3));
	}
	
	@DisplayName("[/brands/{brandName}/models] - will redirect to ModelController")
	@Test
	void test_getModelByBrandName_ShouldRedirect() throws Exception {

		// given
		ModelResponse response = new ModelResponse(TOYOTA);
		List<ModelResponse> dtos = List.of(response);
		// when
		BDDMockito.given(modelService.getModelResponseByBrandName(ArgumentMatchers.any()))
				.willReturn(new PageImpl<>(dtos));

		// then
		mockMvc.perform(
				get("/brands/{brandName}/models", TOYOTA)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is3xxRedirection());
	}

}
