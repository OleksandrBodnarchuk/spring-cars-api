package pl.alex.cars.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
	
	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper mapper;
	
	@MockBean private BrandService brandService;
	
	
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
