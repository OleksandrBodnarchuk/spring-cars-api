package pl.alex.cars.api.model;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.alex.cars.api.ApiV0;

@Controller
public class ModelController implements ApiV0, ModelApi {

  private final ModelService modelService;

  public ModelController(ModelService modelService) {
    this.modelService = modelService;
  }

  @Override
  public ResponseEntity<Page<ModelResponse>> getModelsByBrand(
      @PathVariable("brand") String brandName,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "10") Integer size) {
    Page<ModelResponse> response = modelService.getModelResponseByBrandName(
        ModelRequestQuery.of(brandName, page, size));
    return ResponseEntity.ok(response);
  }

  @Override
  public ResponseEntity<?> sendRedirrect(@PathVariable("name") String modelName) {
    return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
        .header(HttpHeaders.LOCATION, "/submodels/" + modelName).build();
  }
}
