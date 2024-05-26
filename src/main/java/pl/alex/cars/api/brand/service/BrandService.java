package pl.alex.cars.api.brand.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.alex.cars.api.brand.dto.BrandRequest;
import pl.alex.cars.api.brand.dto.BrandResponse;
import pl.alex.cars.api.brand.entity.Brand;
import pl.alex.cars.api.brand.repository.BrandRepository;
import pl.alex.cars.mapper.BrandMapper;

@Service
public class BrandService {

	private final BrandRepository brandRepository;

	public BrandService(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}

	public Page<BrandResponse> findAllBrands(Pageable pageable) {
		List<BrandResponse> dtos = new ArrayList<>();
		Page<Brand> brands = brandRepository.findAll(pageable);

		brands.forEach(brand -> dtos.add(BrandMapper.INSTANSE.convertToDto(brand)));

		return new PageImpl<>(dtos, brands.getPageable(), brands.getTotalElements());
	}

	// TODO: create custom exception
	public BrandResponse getBrandResponseByName(String name) {
		Brand brand = brandRepository.findByNameEquals(name)
				.orElseThrow(() -> new IllegalArgumentException("Brand with name [" + name + "] Not found."));
		return BrandMapper.INSTANSE.convertToDto(brand);
	}

	public List<BrandResponse> getMultipleBrands(BrandRequest brandRequest) {
		List<Brand> brands = brandRepository.findBrandByNameIn(brandRequest.getNames());
		return brands.stream()
				.map(BrandMapper.INSTANSE::convertToDto)
				.collect(Collectors.toList());
	}
	
}
