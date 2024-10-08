package pl.alex.cars.api.model.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.alex.cars.exceptions.InvalidDataException;

public record ModelRequestQuery(String brandName, Pageable pageable) {

  public static ModelRequestQuery of(String brandName, Integer page, Integer size) {
    if (page == null || page < 0) {
      // FIXME: fix exception handling and add localization
      throw new InvalidDataException("Page value must be greater than 0");
    }
    if (size == null || size < 1 || size > 200) {
      // FIXME: fix exception handling and add localization
      throw new InvalidDataException("Size value must be between 1-200");
    }
    return new ModelRequestQuery(correctBrandName(brandName), PageRequest.of(page, size));
  }

  private static String correctBrandName(String brandName) {
    return brandName.substring(0, 1).toUpperCase() + brandName.substring(1).toLowerCase();
  }
}
