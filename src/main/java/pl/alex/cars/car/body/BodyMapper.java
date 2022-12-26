package pl.alex.cars.car.body;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.extract.dto.BodyExtractDto;

@Mapper
public interface BodyMapper {

	BodyMapper INSTANCE = Mappers.getMapper(BodyMapper.class);
	Body convertToEntity(BodyExtractDto dto);
}
