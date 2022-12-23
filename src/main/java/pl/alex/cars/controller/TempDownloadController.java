package pl.alex.cars.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.alex.cars.service.LogoService;
import pl.alex.cars.service.ModelService;

@RestController
@RequestMapping("/download")
public class TempDownloadController {
	
	private final LogoService logoService;
	private final ModelService modelService;
	
	public TempDownloadController(LogoService logoService, ModelService modelService) {
		this.logoService = logoService;
		this.modelService = modelService;
	}

	public ResponseEntity<String> saveLogos(){
		return ResponseEntity.ok("Saved");
	}
	
	public ResponseEntity<String> saveModels() {
		return ResponseEntity.ok("Saved");
	}
}
