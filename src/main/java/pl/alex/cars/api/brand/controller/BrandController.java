package pl.alex.cars.api.brand.controller;

import java.io.IOException;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import pl.alex.cars.api.ApiV0;
import pl.alex.cars.api.brand.BrandApi;
import pl.alex.cars.api.brand.dto.BrandRequest;
import pl.alex.cars.api.brand.dto.BrandResponse;
import pl.alex.cars.api.brand.service.BrandService;

@Slf4j
@Controller
@RequiredArgsConstructor
class BrandController implements ApiV0, BrandApi {

  private final String className = this.getClass().getSimpleName();

  private final MessageSource messageSource;
  private final BrandService brandService;

  @Override
  public ResponseEntity<?> getMultipleBrands(BrandRequest brandRequest) {
    log.info("[{}] - getMultipleBrands() - called", className);
    if (brandRequest.getNames() == null || brandRequest.getNames().isEmpty()) {
      return ResponseEntity.badRequest()
          .body(messageSource.getMessage("brand.name.list.rest.validation", null,
              Locale.getDefault()));
    }
    return ResponseEntity.ok(brandService.getMultipleBrands(brandRequest));
  }

  @Override
  public ResponseEntity<?> sendRedirrect(String brandName)
      throws IOException {
    log.info("[{}] - sendRedirrect() - called", className);
    return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
        .header(HttpHeaders.LOCATION, "/models/" + brandName).build();
  }

  @Override
  public ResponseEntity<BrandResponse> getBrandByName(String brandName) {
    log.info("[{}] - getBrandByName() - called", className);
    BrandResponse response = brandService.getBrandResponseByName(brandName);
    return ResponseEntity.ok(response);
  }

  @Override
  public ResponseEntity<Page<BrandResponse>> getAllBrands(Integer page, Integer size) {
    log.info("[{}] - getAllBrands() - called", className);
    Page<BrandResponse> brands = brandService.findAllBrands(PageRequest.of(page, size));
    return ResponseEntity.ok(brands);
  }

}
