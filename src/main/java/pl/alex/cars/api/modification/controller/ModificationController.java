package pl.alex.cars.api.modification.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import pl.alex.cars.api.ApiV0;
import pl.alex.cars.api.modification.ModificationApi;
import pl.alex.cars.api.modification.dto.ModificationResponse;
import pl.alex.cars.api.modification.service.ModificationService;

@Controller
public class ModificationController implements ApiV0, ModificationApi {

  private final ModificationService modificationService;

  public ModificationController(ModificationService modificationService) {
    this.modificationService = modificationService;
  }


  @Override
  public ResponseEntity<Page<ModificationResponse>> getModelsByBrand(
      @PathVariable("model") String modelName) {
    Page<ModificationResponse> response = modificationService.getSubModelResponseByBrandName(modelName);
    return ResponseEntity.ok(response);
  }

}
