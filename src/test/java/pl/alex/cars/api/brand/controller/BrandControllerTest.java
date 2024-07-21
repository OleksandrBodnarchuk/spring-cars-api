package pl.alex.cars.api.brand.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.http.MediaType;
import pl.alex.cars.api.TestEntityFactory;
import pl.alex.cars.api.WebTestUtil;
import pl.alex.cars.api.brand.dto.BrandRequest;
import pl.alex.cars.api.brand.dto.BrandResponse;
import pl.alex.cars.api.brand.entity.Brand;
import pl.alex.cars.api.brand.exception.BrandNotFoundException;
import pl.alex.cars.api.model.dto.ModelResponse;

@WebMvcTest(BrandController.class)
class BrandControllerTest extends WebTestUtil {

  private static final String API_VERSION = "/v0";

  private static final List<Brand> BRANDS = TestEntityFactory.createBrandsWithModels(5, 3);
  private static final Brand brand1 = BRANDS.get(0);
  private static final Brand brand2 = BRANDS.get(1);

  private static final BrandResponse brand1Response = BrandResponse.of(brand1.getId(), brand1.getName(),
      brand1.getModels().stream()
          .map(model -> new ModelResponse(model.getId(), model.getName()))
          .toList());

  private static final BrandResponse brand2Response = BrandResponse.of(brand2.getId(), brand2.getName(),
      brand2.getModels().stream()
          .map(model -> new ModelResponse(model.getId(), model.getName()))
          .toList());

  @DisplayName("[/brands/multiple] - will return multiple brands matched by their names")
  @Test
  void test_getMultipleBrands_shouldReturn_dto_list() throws Exception {
    // given
    BrandRequest request = new BrandRequest();
    request.setNames(List.of(brand1.getName(), brand2.getName()));

    // when
    BDDMockito.given(brandService.getMultipleBrands(ArgumentMatchers.any(BrandRequest.class)))
        .willReturn(List.of(brand1Response, brand2Response));

    // then
    mockMvc.perform(post(API_VERSION + "/brands/multiple")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value(brand1.getName()))
        .andExpect(jsonPath("$[1].name").value(brand2.getName()));
  }

  @DisplayName("[/brands/multiple] - will FAIL on validation")
  @Test
  void test_getMultipleBrands_shouldFAIL_onValidation() throws Exception {
    // given
    BrandRequest request = new BrandRequest();
    request.setNames(List.of()); // Empty list to trigger validation failure

    // then
    mockMvc.perform(post(API_VERSION + "/brands/multiple")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @DisplayName("[/brands/{name}] - will return 1 dto matched by name with models")
  @Test
  void test_getBrandByName_shouldReturn_dto_withModels() throws Exception {
    // given
    BDDMockito.given(brandService.getBrandResponseByName(brand1.getName(), true))
        .willReturn(brand1Response);

    // then
    mockMvc.perform(get(API_VERSION + "/brands/{name}", brand1.getName())
            .param("includeModels", "true") // Include models
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(brand1.getName()))
        .andExpect(jsonPath("$.models").isArray())
        .andExpect(jsonPath("$.models[0].name").value(brand1.getModels().get(0).getName()));
  }

  @DisplayName("[/brands/{name}] - will return 1 dto matched by name without models")
  @Test
  void test_getBrandByName_shouldReturn_dto_withoutModels() throws Exception {
    // Given
    BrandResponse brandResponse = BrandResponse.of(brand1.getId(), brand1.getName(), List.of());
    BDDMockito.given(brandService.getBrandResponseByName(brand1.getName(), false))
        .willReturn(brandResponse);

    // When
    mockMvc.perform(get(API_VERSION + "/brands/{name}", brand1.getName())
            .param("includeModels", "false")
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(brand1.getName()))
        .andExpect(jsonPath("$.models").isArray())
        .andExpect(jsonPath("$.models.length()").value(0));
  }

  @DisplayName("[/brands] - will return list of brands")
  @Test
  void test_getBrands_shouldReturn_list() throws Exception {
    // given
    BDDMockito.given(brandService.findAllBrands())
        .willReturn(List.of(brand1Response, brand2Response));

    // then
    mockMvc.perform(get(API_VERSION + "/brands")
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].name").value(brand1.getName()))
        .andExpect(jsonPath("$[1].name").value(brand2.getName()));
  }

  @DisplayName("[/brands] - will return empty list when no brands found")
  @Test
  void test_getBrands_shouldReturn_emptyList() throws Exception {
    // given
    BDDMockito.given(brandService.findAllBrands())
        .willReturn(List.of());

    // then
    mockMvc.perform(get(API_VERSION + "/brands")
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(0));
  }

  @DisplayName("[/brands/{name}] - will return 404 if brand not found")
  @Test
  void test_getBrandByName_shouldReturn_404_ifNotFound() throws Exception {
    // given
    BDDMockito.given(brandService.getBrandResponseByName("UNKNOWN", true))
        .willThrow(new BrandNotFoundException("Brand not found"));

    // then
    mockMvc.perform(get(API_VERSION + "/brands/{name}", "UNKNOWN")
            .param("includeModels", "true") // Expecting a parameter
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Brand not found"));
  }
}
