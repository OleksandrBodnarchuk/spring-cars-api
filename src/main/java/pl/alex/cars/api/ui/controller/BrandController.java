package pl.alex.cars.api.ui.controller;

import java.io.IOException;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.alex.cars.car.brand.BrandRequest;
import pl.alex.cars.car.brand.BrandResponse;
import pl.alex.cars.car.brand.BrandService;

@Slf4j
@RestController
@RequestMapping("/brands")
public class BrandController {

	private final String className = this.getClass().getSimpleName();
	
	private final MessageSource messageSource;
	private final BrandService brandService;
	
	public BrandController(BrandService brandService, MessageSource messageSource) {
		this.messageSource = messageSource;
		this.brandService = brandService;
	}
	
	@PostMapping("/brands")
	public ResponseEntity<?> getMultipleBrands(@RequestBody BrandRequest brandRequest){
		log.info("[" + className + "] - getMultipleBrands() - called"  );
		if (brandRequest.getNames() == null || brandRequest.getNames().isEmpty()) {
			return ResponseEntity.badRequest()
					.body(messageSource.getMessage("brand.name.list.rest.validation", null, Locale.getDefault()));
		}
		return ResponseEntity.ok(brandService.getMultipleBrands(brandRequest));
	}
	
	@GetMapping("/{name}/models")
	public ResponseEntity<?> sendRedirrect(@PathVariable("name") String brandName) throws IOException {
		log.info("[" + className + "] - sendRedirrect() - called"  );
		return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
				.header(HttpHeaders.LOCATION, "/models/" + brandName).build();
	}
	
	@PostMapping("/{name}")
	public ResponseEntity<BrandResponse> getBrandByName(@PathVariable("name") String brandName) {
		log.info("[" + className + "] - getBrandByName() - called"  );
		BrandResponse response = brandService.getBrandResponseByName(brandName);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Page<BrandResponse>> getAllBrands(@RequestBody BrandRequest brandRequest) {
		log.info("[" + className + "] - getAllBrands() - called"  );
		Page<BrandResponse> brands = brandService.findAllBrands(brandRequest);
		return ResponseEntity.ok(brands);
	}

}
