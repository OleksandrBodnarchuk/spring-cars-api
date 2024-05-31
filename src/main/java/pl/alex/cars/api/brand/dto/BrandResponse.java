package pl.alex.cars.api.brand.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandResponse {
  private String name;
  private List<String> models;

  public static BrandResponse of(String name, List<String> models) {
    return BrandResponse.builder()
        .name(name)
        .models(models)
        .build();
  }
}
