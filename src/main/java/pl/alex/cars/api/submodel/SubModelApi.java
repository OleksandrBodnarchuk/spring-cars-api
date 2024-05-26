package pl.alex.cars.api.submodel;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Validated
public interface SubModelApi {

  @RequestMapping(value = "/submodels/{model}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<Page<SubModelResponse>> getModelsByBrand(@PathVariable("model") String modelName);
}
