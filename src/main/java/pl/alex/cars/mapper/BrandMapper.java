package pl.alex.cars.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.api.brand.entity.Brand;
import pl.alex.cars.api.brand.dto.BrandResponse;

@Mapper //(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandMapper {
	BrandMapper INSTANSE = Mappers.getMapper(BrandMapper.class);

	@Mapping(source = "name", target = "name")
	BrandResponse convertToDto(Brand brand);
}
