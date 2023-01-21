package pl.alex.cars.car.brand;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.alex.cars.mapper.BrandMapper;
import pl.alex.cars.utils.CommonUtils;

@Service
public class BrandService {

	private final BrandRepository brandRepository;

	public BrandService(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}

	public Page<BrandResponse> findAllBrands(BrandRequest brandRequest) {
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
	
}
