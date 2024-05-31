package pl.alex.cars.api.modification;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.alex.cars.api.modification.dto.ModificationResponse;

@Validated
public interface ModificationApi {

  @RequestMapping(value = "/submodels/{model}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<Page<ModificationResponse>> getModelsByBrand(@PathVariable("model") String modelName);
}
