package pl.alex.cars.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.alex.cars.dto.ManufacturerDto;
import pl.alex.cars.service.ManufacturerService;

@RestController(value = "/manufacturer")
public class ManufacturerController {

	private ManufacturerService manufacturerService;
	
	public ManufacturerController(ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}


	@PostMapping
	public ResponseEntity<?> saveMultipleManufacturers(List<ManufacturerDto> dtos) {
		manufacturerService.saveAllManufacturerDtos(dtos);
		return ResponseEntity.ok("Saved");
	}
}
