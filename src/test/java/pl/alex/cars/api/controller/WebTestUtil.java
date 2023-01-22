package pl.alex.cars.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.alex.cars.car.brand.BrandService;
import pl.alex.cars.car.model.ModelService;

public class WebTestUtil {

	
	protected static final String BMW = "BMW";
	protected static final String AUDI = "AUDI";
	protected static final String TOYOTA = "TOYOTA";
	
	@Autowired protected MockMvc mockMvc;
	@Autowired protected ObjectMapper mapper;
	
	@MockBean protected BrandService brandService;
	@MockBean protected ModelService modelService;
	
}
