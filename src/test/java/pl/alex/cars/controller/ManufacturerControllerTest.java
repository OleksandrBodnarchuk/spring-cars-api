package pl.alex.cars.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.alex.cars.car.brand.BrandDto;
import pl.alex.cars.car.brand.BrandService;

@SpringBootTest
class ManufacturerControllerTest {

	@MockBean 
	BrandService manufacturerService;
	
	@Autowired
	WebApplicationContext applicationContext;

	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(applicationContext)
				.build();
	}

//	@Test
//	void when_SaveMultipleManufacturers_thenOK() throws Exception {
//		List<ManufacturerDto> dtos = Arrays.asList();
//		
//		when(manufacturerService.saveAllManufacturerDtos(dtos)).thenReturn(true);
//		
//		mockMvc.perform(post("/manufacturer/list")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(mapper.writeValueAsString(dtos)))
//		.andDo(print())
//		.andExpect(status().isOk());
//	}
//	
//	@Test
//	void when_SaveEmptyManufacturers_thenBadRequest() throws Exception {
//		List<ManufacturerDto> dtos = Arrays.asList();
//		
//		when(manufacturerService.saveAllManufacturerDtos(dtos)).thenReturn(false);
//		
//		mockMvc.perform(post("/manufacturer/list")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(mapper.writeValueAsString(dtos)))
//		.andDo(print())
//		.andExpect(status().isBadRequest());
//	}

}
