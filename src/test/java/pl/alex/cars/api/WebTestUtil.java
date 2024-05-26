package pl.alex.cars.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.alex.cars.api.brand.service.BrandService;
import pl.alex.cars.api.model.ModelRepository;
import pl.alex.cars.api.model.ModelService;
import pl.alex.cars.api.submodel.SubModelService;

public class WebTestUtil {


  protected static final String BMW = "BMW";
  protected static final String AUDI = "AUDI";
  protected static final String TOYOTA = "TOYOTA";

  @Autowired
  protected MockMvc mockMvc;
  @Autowired
  protected ObjectMapper mapper;

  @MockBean
  protected BrandService brandService;
  @MockBean
  protected ModelService modelService;
  @MockBean
  protected SubModelService subModelService;
  @MockBean
  protected ModelRepository modelRepository;

}
