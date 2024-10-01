package pl.alex.cars.api.brand.ui;

import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.alex.cars.api.ApiV0;

@Slf4j
@RestController
@RequiredArgsConstructor
class BrandController implements ApiV0 {

  private final String className = this.getClass().getSimpleName();

  private final MessageSource messageSource;
  private final BrandService brandService;

  @RequestMapping(value = "/brands/multiple",
      produces = {"application/json"},
      method = RequestMethod.POST)
  public ResponseEntity<?> getMultipleBrands(@RequestBody BrandRequest brandRequest) {
    log.info("[{}] - getMultipleBrands() - called", className);
    //FIXME: add @Valid if possible
    if (brandRequest.getNames() == null || brandRequest.getNames().isEmpty()) {
      return ResponseEntity.badRequest()
          .body(messageSource.getMessage("brand.name.list.rest.validation", null,
              Locale.getDefault()));
    }
    return ResponseEntity.ok(brandService.getMultipleBrands(brandRequest));
  }

  @RequestMapping(value = "/brands/{name}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  public ResponseEntity<BrandResponse> getBrandByName(
      @PathVariable("name") String brandName,
      @RequestParam(name = "includeModels", defaultValue = "true") boolean includeModels) {

    log.info("[{}] - getBrandByName() - called", brandName);
    BrandResponse brandResponse = brandService.getBrandResponseByName(brandName, includeModels);
    return ResponseEntity.ok(brandResponse);
  }

  @RequestMapping(value = "/brands",
      produces = {"application/json"},
      method = RequestMethod.GET)
  public ResponseEntity<List<BrandResponse>> getBrands() {
    log.info("[{}] - getAllBrands() - called", className);
    return ResponseEntity.ok(brandService.findAllBrands());
  }

}
