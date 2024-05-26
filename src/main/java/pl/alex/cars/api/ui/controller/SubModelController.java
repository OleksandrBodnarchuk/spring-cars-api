package pl.alex.cars.api.ui.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.alex.cars.car.submodel.SubModelResponse;
import pl.alex.cars.car.submodel.SubModelService;

@RestController
@RequestMapping("/submodels")
public class SubModelController {

	private final SubModelService subModelService;

	public SubModelController(SubModelService subModelService) {
		this.subModelService = subModelService;
	}
	
	
	@GetMapping("/{model}")
	public ResponseEntity<Page<SubModelResponse>> getModelsByBrand(@PathVariable("model") String modelName){
		Page<SubModelResponse> response =  subModelService.getSubModelResponseByBrandName(modelName);
		return ResponseEntity.ok(response);
	}
	
}
