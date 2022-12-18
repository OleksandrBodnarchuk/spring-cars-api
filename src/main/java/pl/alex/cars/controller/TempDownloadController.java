package pl.alex.cars.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.alex.cars.service.LogoService;
import pl.alex.cars.service.ModelService;

@RestController("/download")
public class TempDownloadController {
	
	private final LogoService logoService;
	private final ModelService modelService;
	
	public TempDownloadController(LogoService logoService, ModelService modelService) {
		this.logoService = logoService;
		this.modelService = modelService;
	}

	@GetMapping ("/logos")
	public ResponseEntity<String> saveLogos(){
		logoService.getLogoPictures();
		return ResponseEntity.ok("Saved");
	}
	
	@GetMapping("/models")
	public ResponseEntity<String> saveModels() {
		modelService.getModels();
		return ResponseEntity.ok("Saved");
	}
}
