package pl.alex.cars.api.model.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import pl.alex.cars.api.WebTestUtil;
import pl.alex.cars.api.model.dto.ModelResponse;

@WebMvcTest(ModelController.class)
class ModelControllerTest extends WebTestUtil {

  private static final String TOYOTA = "TOYOTA";

  private List<ModelResponse> modelResponses;
  private Page<ModelResponse> pageResponse;

  @BeforeEach
  void setUp() {
    // Using the factory to create test data
    modelResponses = List.of(
        new ModelResponse(1L, "Avensis"),
        new ModelResponse(2L, "Hilux"),
        new ModelResponse(3L, "Corolla")
    );
    pageResponse = new PageImpl<>(modelResponses);
  }

  @DisplayName("[/v0/models/{brand}] - will return models dto matched by brand name")
  @Test
  void testGetModelsByBrand() throws Exception {
    // Given
    BDDMockito.given(modelService.getModelResponseByBrandName(ArgumentMatchers.any()))
        .willReturn(pageResponse);

    // Then
    mockMvc.perform(
            get("/v0/models/{brand}", TOYOTA)
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content").isArray())
        .andExpect(jsonPath("$.numberOfElements").value(modelResponses.size()))
        .andExpect(jsonPath("$.content[0].name").value("Avensis"))
        .andExpect(jsonPath("$.content[1].name").value("Hilux"))
        .andExpect(jsonPath("$.content[2].name").value("Corolla"));
  }

  @DisplayName("[/v0/models/{brand}] - will return empty list if no models are found")
  @Test
  void testGetModelsByBrandEmpty() throws Exception {
    // Given
    Page<ModelResponse> emptyPageResponse = Page.empty();

    BDDMockito.given(modelService.getModelResponseByBrandName(ArgumentMatchers.any()))
        .willReturn(emptyPageResponse);

    // Then
    mockMvc.perform(
            get("/v0/models/{brand}", "UNKNOWN_BRAND")
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content").isArray())
        .andExpect(jsonPath("$.numberOfElements").value(0));
  }

  @DisplayName("[/v0/models/{brand}] - will return bad request for invalid parameters")
  @Test
  void testGetModelsByBrandBadRequest() throws Exception {
    // Given - invalid page size
    mockMvc.perform(
            get("/v0/models/{brand}", TOYOTA)
                .param("page", "-1")
                .param("size", "-10")
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @DisplayName("[/v0/models/{name}/modifications] - will return redirect for model modifications")
  @Test
  void testSendRedirect() throws Exception {
    // Then
    mockMvc.perform(
            get("/v0/models/{name}/modifications", "Corolla")
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isMovedPermanently())
        .andExpect(header().string(HttpHeaders.LOCATION, "/submodels/Corolla"));
  }
}
