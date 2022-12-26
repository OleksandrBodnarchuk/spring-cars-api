package pl.alex.cars.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.alex.cars.service.ExtractUtils;

@RestController
@RequestMapping("/download")
public class TempDownloadController {
	
	@GetMapping("/all")
	public ResponseEntity<String> extractAll() {
		ExtractUtils extractService = new ExtractUtils();
		extractService.extractAll();
		return ResponseEntity.ok("Saved");
	}
}
