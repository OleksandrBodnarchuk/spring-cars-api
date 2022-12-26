package pl.alex.cars.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.alex.cars.car.brand.BrandDto;
import pl.alex.cars.car.brand.BrandService;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

	private BrandService manufacturerService;

	public ManufacturerController(BrandService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}
	
//	@PostMapping("/list")
//	public ResponseEntity<String> saveMultipleManufacturers(@RequestBody List<ManufacturerDto> dtos) {
//		if (manufacturerService.saveAllManufacturerDtos(dtos)) {
//			return ResponseEntity.ok("Saved");
//		} else {
//			return new ResponseEntity<String>("Not saved", HttpStatus.BAD_REQUEST);
//		}
//	}
}
