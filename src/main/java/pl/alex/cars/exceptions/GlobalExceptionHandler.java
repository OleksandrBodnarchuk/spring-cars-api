package pl.alex.cars.exceptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.alex.cars.api.brand.exception.BrandNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BrandNotFoundException.class)
  public ResponseEntity<?> handleBrandNotFoundException(BrandNotFoundException ex, WebRequest request) {
    Map<String, String> response = new HashMap<>();
    response.put("message", ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidDataException.class)
  public ResponseEntity<?> handleInvalidDataException(InvalidDataException ex, WebRequest request) {
    Map<String, String> response = new HashMap<>();
    response.put("message", ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

}
