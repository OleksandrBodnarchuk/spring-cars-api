package pl.alex.cars.api.model;

import java.io.IOException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
public interface ModelApi {

  @RequestMapping(value = "/models/{brand}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<Page<ModelResponse>> getModelsByBrand(
      @PathVariable("brand") String brandName,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "10") Integer size);

  @RequestMapping(value = "/models/{name}/modifications",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<?> sendRedirrect(@PathVariable("name") String modelName) throws IOException;
}
