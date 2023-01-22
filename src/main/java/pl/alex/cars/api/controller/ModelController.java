package pl.alex.cars.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.alex.cars.car.model.ModelResponse;
import pl.alex.cars.car.model.ModelService;

@RestController
@RequestMapping("/models")
public class ModelController {

	private final ModelService modelService;
	
	public ModelController(ModelService modelService) {
		this.modelService = modelService;
	}

	@GetMapping("/{brand}")
	public ResponseEntity<Page<ModelResponse>> getModelByBrand(@PathVariable("brand") String brandName){
		Page<ModelResponse> response =  modelService.getModelResponseByBrandName(brandName);
		return ResponseEntity.ok(response);
	}
}
