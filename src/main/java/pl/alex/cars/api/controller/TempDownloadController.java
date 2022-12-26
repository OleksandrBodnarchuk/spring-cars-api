package pl.alex.cars.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.alex.cars.car.CarService;
import pl.alex.cars.extract.ExtractUtils;

@RestController
@RequestMapping("/download")
public class TempDownloadController {
	private final CarService carService;
	
	public TempDownloadController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/all")
	public ResponseEntity<String> extractAll() {
		carService.fillDatabase();
		return ResponseEntity.ok("Saved");
	}
}
