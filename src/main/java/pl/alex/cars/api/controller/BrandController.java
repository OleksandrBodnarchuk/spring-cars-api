package pl.alex.cars.api.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.alex.cars.car.brand.BrandRequest;
import pl.alex.cars.car.brand.BrandResponse;
import pl.alex.cars.car.brand.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {

	private final MessageSource messageSource;
	private final BrandService brandService;
	
	public BrandController(BrandService brandService, MessageSource messageSource) {
		this.messageSource = messageSource;
		this.brandService = brandService;
	}
	
	@PostMapping("/brands")
	public ResponseEntity<?> getMultipleBrands(@RequestBody BrandRequest brandRequest){
		if (brandRequest.getNames().isEmpty()) {
			return ResponseEntity.badRequest()
					.body(messageSource.getMessage("brand.name.list.rest.validation", null, Locale.getDefault()));
		}
		Page<BrandResponse> response = brandService.getMultipleBrands(brandRequest);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/{name}")
	public ResponseEntity<BrandResponse> getBrandByName(@PathVariable("name") String name){
		BrandResponse response = brandService.getBrandResponseByName(name);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Page<BrandResponse>> getAllBrands(@RequestBody BrandRequest brandRequest) {
		Page<BrandResponse> brands = brandService.findAllBrands(brandRequest);
		return ResponseEntity.ok(brands);
	}

}
