package pl.alex.cars.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.dto.ManufacturerDto;
import pl.alex.cars.entity.Manufacturer;

@Mapper
public interface ManufacturerMapper {
	ManufacturerMapper INSTANCE = Mappers.getMapper(ManufacturerMapper.class);

	Manufacturer convertToEntity(ManufacturerDto dto);
}
