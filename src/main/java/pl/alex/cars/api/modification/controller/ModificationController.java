package pl.alex.cars.api.modification.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.alex.cars.api.ApiV0;
import pl.alex.cars.api.modification.dto.ModificationResponse;
import pl.alex.cars.api.modification.service.ModificationService;

@RestController
public class ModificationController implements ApiV0 {

  private final ModificationService modificationService;

  public ModificationController(ModificationService modificationService) {
    this.modificationService = modificationService;
  }


  @RequestMapping(value = "/submodels/{model}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  public ResponseEntity<Page<ModificationResponse>> getModelsByBrand(
      @PathVariable("model") String modelName) {
    Page<ModificationResponse> response = modificationService.getSubModelResponseByBrandName(
        modelName);
    return ResponseEntity.ok(response);
  }

}
