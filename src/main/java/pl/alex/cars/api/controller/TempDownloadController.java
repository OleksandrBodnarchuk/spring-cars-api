package pl.alex.cars.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.alex.cars.extract.ExtractUtils;

@RestController
@RequestMapping("/download")
public class TempDownloadController {
	private final ExtractUtils extractUtils;

	public TempDownloadController(ExtractUtils extractUtils) {
		this.extractUtils = extractUtils;
	}


	@GetMapping("/all")
	public ResponseEntity<String> extractAll() {
		extractUtils.extractAll();
		return ResponseEntity.ok("Saved");
	}
}
