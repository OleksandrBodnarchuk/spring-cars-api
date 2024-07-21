package pl.alex.cars.api.model.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.alex.cars.api.ApiV0;
import pl.alex.cars.api.model.dto.ModelRequestQuery;
import pl.alex.cars.api.model.dto.ModelResponse;
import pl.alex.cars.api.model.service.ModelService;

@RestController
@RequiredArgsConstructor
public class ModelController implements ApiV0 {

  private final ModelService modelService;

  @RequestMapping(value = "/models/{brand}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  public ResponseEntity<Page<ModelResponse>> getModelsByBrand(
      @PathVariable("brand") String brandName,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "10") Integer size) {
    Page<ModelResponse> response = modelService.getModelResponseByBrandName(
        ModelRequestQuery.of(brandName, page, size));
    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/models/{name}/modifications",
      produces = {"application/json"},
      method = RequestMethod.GET)
  public ResponseEntity<?> sendRedirrect(@PathVariable("name") String modelName) {
    return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
        .header(HttpHeaders.LOCATION, "/submodels/" + modelName).build();
  }
}
