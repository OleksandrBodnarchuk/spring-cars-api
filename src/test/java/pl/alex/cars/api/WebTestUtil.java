package pl.alex.cars.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.alex.cars.api.brand.service.BrandService;
import pl.alex.cars.api.model.repository.ModelRepository;
import pl.alex.cars.api.model.service.ModelService;
import pl.alex.cars.api.modification.service.ModificationService;

public class WebTestUtil {


  @Autowired
  protected MockMvc mockMvc;
  @Autowired
  protected ObjectMapper mapper;

  @MockBean
  protected BrandService brandService;
  @MockBean
  protected ModelService modelService;
  @MockBean
  protected ModificationService modificationService;
  @MockBean
  protected ModelRepository modelRepository;

}
