package pl.alex.cars.car;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.alex.cars.car.brand.Brand;
import pl.alex.cars.car.brand.BrandMapper;
import pl.alex.cars.extract.ExtractUtils;
import pl.alex.cars.extract.dto.BrandExtractDto;

@Service
public class CarService {

	public void fillDatabase() {
		ExtractUtils extractService = new ExtractUtils();
		List<BrandExtractDto> dtos = extractService.extractAll();
		List<Brand> brands = dtos.stream().map(BrandMapper.INSTANCE::convertToEntity).collect(Collectors.toList());
		System.out.println("");
	}

}
