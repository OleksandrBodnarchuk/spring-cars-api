package pl.alex.cars.api.brand.ui;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import pl.alex.cars.api.model.dto.ModelResponse;

@Getter
@Builder
class BrandResponse {
  private Long id;
  private String name;
  private List<ModelResponse> models;

  public static BrandResponse of(Long id, String name, List<ModelResponse> models) {
    return BrandResponse.builder()
        .id(id)
        .name(name)
        .models(models)
        .build();
  }
}
