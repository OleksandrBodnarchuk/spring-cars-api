package pl.alex.cars.api.brand.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BrandNotFoundException extends RuntimeException {

  public BrandNotFoundException(String brandNotFound) {
    super(brandNotFound);
  }
}
