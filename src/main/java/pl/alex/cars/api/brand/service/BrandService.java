package pl.alex.cars.api.brand.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import pl.alex.cars.api.brand.dto.BrandRequest;
import pl.alex.cars.api.brand.dto.BrandResponse;
import pl.alex.cars.api.brand.entity.Brand;
import pl.alex.cars.api.brand.repository.BrandRepository;
import pl.alex.cars.api.model.entity.Model;

@Service
public class BrandService {

	private final BrandRepository brandRepository;

	public BrandService(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}

	public List<String> findAllBrands() {
		return brandRepository.findAll().stream()
				.map(Brand::getName)
				.collect(Collectors.toList());
	}

	// TODO: create custom exception
	public BrandResponse getBrandResponseByName(String name) {
		Brand brand = brandRepository.findByNameEquals(name)
				.orElseThrow(() -> new IllegalArgumentException("Brand with name [" + name + "] Not found."));
		List<String> brandModels = brand.getModels()
				.stream()
				.map(Model::getName)
				.toList();
		return BrandResponse.of(brand.getName(), brandModels);
	}

	public List<String> getMultipleBrands(BrandRequest brandRequest) {
		List<Brand> brands = brandRepository.findBrandByNameIn(brandRequest.getNames());
		return brands.stream()
				.map(Brand::getName)
				.collect(Collectors.toList());
	}

}
