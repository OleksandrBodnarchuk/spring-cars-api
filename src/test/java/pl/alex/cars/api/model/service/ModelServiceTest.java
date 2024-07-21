package pl.alex.cars.api.model.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import pl.alex.cars.api.brand.entity.Brand;
import pl.alex.cars.api.model.dto.ModelRequestQuery;
import pl.alex.cars.api.model.dto.ModelResponse;
import pl.alex.cars.api.model.entity.Model;
import pl.alex.cars.api.model.repository.ModelRepository;

class ModelServiceTest {

  public static final Brand TOYOTA = Brand.builder().name("TOYOTA").build();
  @Mock
  private ModelRepository modelRepository;

  @InjectMocks
  private ModelService modelService;

  private Model toyotaModel1;
  private Model toyotaModel2;
  private ModelResponse toyotaResponse1;
  private ModelResponse toyotaResponse2;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    toyotaModel1 = new Model();
    toyotaModel1.setName("Corolla");
    toyotaModel1.setBrand(TOYOTA);

    toyotaModel2 = new Model();
    toyotaModel2.setName("Camry");
    toyotaModel1.setBrand(TOYOTA);

    toyotaResponse1 = new ModelResponse(1L, "Corolla");
    toyotaResponse2 = new ModelResponse(2L, "Camry");
  }

  @DisplayName("[getModelResponseByBrandName] - should return models page by brand name")
  @Test
  void testGetModelResponseByBrandName() {
    // given
    ModelResponse modelResponse1 = new ModelResponse(1L, toyotaResponse1.name());
    ModelResponse modelResponse2 = new ModelResponse(2L, toyotaResponse2.name());
    Page<ModelResponse> modelPage = new PageImpl<>(List.of(modelResponse1, modelResponse2));

    given(modelRepository.findAllByBrandName("TOYOTA", PageRequest.of(0, 10)))
        .willReturn(modelPage);

    ModelRequestQuery modelRequestQuery = ModelRequestQuery.of("TOYOTA", 0, 10);

    // when
    Page<ModelResponse> result = modelService.getModelResponseByBrandName(modelRequestQuery);

    // then
    assertThat(result).isNotEmpty();
    assertThat(result.getContent()).hasSize(2);
    assertThat(result.getContent().get(0).name()).isEqualTo("Corolla");
    assertThat(result.getContent().get(1).name()).isEqualTo("Camry");
  }

  @DisplayName("[getModelsByBrandName] - should return model names list by brand name")
  @Test
  void testGetModelsByBrandName() {
    // given
    List<Model> models = List.of(toyotaModel1, toyotaModel2);
    given(modelRepository.findAllByBrandName("TOYOTA")).willReturn(models);

    // when
    List<String> result = modelService.getModelsByBrandName("TOYOTA");

    // then
    assertThat(result).isNotEmpty();
    assertThat(result).hasSize(2);
    assertThat(result).containsExactlyInAnyOrder("Corolla", "Camry");
  }
}
