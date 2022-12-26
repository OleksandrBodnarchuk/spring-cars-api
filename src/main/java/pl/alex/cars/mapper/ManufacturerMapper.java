package pl.alex.cars.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.dto.ManufacturerDto;
import pl.alex.cars.entity.Brand;

@Mapper
public interface ManufacturerMapper {
	ManufacturerMapper INSTANCE = Mappers.getMapper(ManufacturerMapper.class);

//	@Mapping(source = "value", target = "manufacturer_value")
//	Manufacturer convertToEntity(ManufacturerDto dto);
//	@Mapping(source = "manufacturer_value", target = "value")
//	ManufacturerDto convertToDto(Manufacturer manufacturer);
}
