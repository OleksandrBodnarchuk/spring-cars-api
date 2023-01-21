package pl.alex.cars.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.alex.cars.car.brand.BrandResponse;
import pl.alex.cars.car.brand.BrandRequest;
import pl.alex.cars.car.brand.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {

	private final BrandService brandService;
	
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}


	@PostMapping
	public ResponseEntity<Page<BrandResponse>> getAllBrands(@RequestBody BrandRequest brandRequest) {
		Page<BrandResponse> brands = brandService.findAllBrands(brandRequest);
		return ResponseEntity.ok(brands);
	}

}
