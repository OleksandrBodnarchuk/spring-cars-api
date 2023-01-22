package pl.alex.cars.api.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.alex.cars.car.brand.BrandRequest;
import pl.alex.cars.car.brand.BrandResponse;
import pl.alex.cars.car.brand.BrandService;

@WebMvcTest
class BrandControllerTest {
	
	private static final String BMW = "BMW";
	private static final String AUDI = "AUDI";
	private static final String TOYOTA = "TOYOTA";
	
	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper mapper;
	
	@MockBean private BrandService brandService;
	
	@DisplayName("[/brands/brands] - will return multiple dtos matched by their names")
	@Test
	void test_getMultipleBrands_shouldReturn_dto_list() throws JsonProcessingException, Exception {
		// given
		BrandRequest request = new BrandRequest();
		request.setNames(List.of(BMW, AUDI, TOYOTA));
		List<BrandResponse> brandResponse = new ArrayList<>(); 
		request.getNames().stream().forEach(name -> {
			BrandResponse response = new BrandResponse();
			response.setName(name);
			brandResponse.add(response);
		});
		
		// when
		BDDMockito.given(brandService.getMultipleBrands(ArgumentMatchers.any(BrandRequest.class))).willReturn(new PageImpl<>(brandResponse));
		
		// then
		mockMvc.perform(post("/brands/brands")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content").isArray())
				.andExpect(jsonPath("$.content").isNotEmpty());
	}
	
	@DisplayName("[/brands/brands] - will FAIL on validation")
	@Test
	void test_getMultipleBrands_shouldFAIL_onValidation() throws JsonProcessingException, Exception {
		// given
		BrandRequest request = new BrandRequest();
		request.setNames(List.of());
		
		// when
		BDDMockito.given(brandService.getMultipleBrands(ArgumentMatchers.any(BrandRequest.class))).willReturn(new PageImpl<>(List.of()));
		
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

}
