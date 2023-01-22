package pl.alex.cars.car.brand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.alex.cars.mapper.BrandMapper;
import pl.alex.cars.utils.CarPageable;
import pl.alex.cars.utils.CommonUtils;

@Service
public class BrandService {

	private final BrandRepository brandRepository;

	public BrandService(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}

	public Page<BrandResponse> findAllBrands(CarPageable brandRequest) {
		Pageable pageable = CommonUtils.createPageable(brandRequest);
		int ordinal = brandRequest.getOrdinal();
		Page<Brand> brands = brandRepository.findAll(pageable);
		List<BrandResponse> dtos = new ArrayList<>();
		for (Brand brand : brands) {
			BrandResponse dto = BrandMapper.INSTANSE.convertToDto(brand);
			dto.setOrdinal(ordinal);
			ordinal++;
			dtos.add(dto);
		}
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
				.map(brand -> BrandMapper.INSTANSE.convertToDto(brand))
				.collect(Collectors.toList());
	}
	
}
