package pl.alex.cars.api.model.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.alex.cars.exceptions.InvalidDataException;

public record ModelRequestQuery(String brandName, Pageable pageable) {

  public static ModelRequestQuery of(String brandName, Integer page, Integer size) {
    if (page == null || page < 0) {
      throw new InvalidDataException("Page value must be greater than 0");
    }
    if (size == null || size < 1 || size > 200) {
      throw new InvalidDataException("Size value must be between 1-200");
    }
    return new ModelRequestQuery(brandName, PageRequest.of(page, size));
  }

}
