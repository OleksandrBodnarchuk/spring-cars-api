package pl.alex.cars.api.brand;

import java.io.IOException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.alex.cars.api.brand.dto.BrandRequest;
import pl.alex.cars.api.brand.dto.BrandResponse;

@Validated
public interface BrandApi {

  @RequestMapping(value = "/brands/multiple",
      produces = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<?> getMultipleBrands(@RequestBody BrandRequest brandRequest);

  @RequestMapping(value = "/brands/{name}/models",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<?> sendRedirrect(@PathVariable("name") String brandName)
      throws IOException;

  @RequestMapping(value = "/brands/{name}",
      produces = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<BrandResponse> getBrandByName(@PathVariable("name") String brandName);

  @RequestMapping(value = "/brands",
      produces = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<Page<BrandResponse>> getAllBrands(@RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "10") Integer size);

}
