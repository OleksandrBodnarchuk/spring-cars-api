package pl.alex.cars.api.submodel;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import pl.alex.cars.api.ApiV0;

@Controller
public class SubModelController implements ApiV0, SubModelApi {

  private final SubModelService subModelService;

  public SubModelController(SubModelService subModelService) {
    this.subModelService = subModelService;
  }


  @Override
  public ResponseEntity<Page<SubModelResponse>> getModelsByBrand(
      @PathVariable("model") String modelName) {
    Page<SubModelResponse> response = subModelService.getSubModelResponseByBrandName(modelName);
    return ResponseEntity.ok(response);
  }

}
