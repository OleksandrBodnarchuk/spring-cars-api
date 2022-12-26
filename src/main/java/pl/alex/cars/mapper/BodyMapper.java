package pl.alex.cars.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.dto.extract.details.BodyExtractDto;
import pl.alex.cars.entity.details.Body;

@Mapper
public interface BodyMapper {

	BodyMapper INSTANCE = Mappers.getMapper(BodyMapper.class);
	Body convertToEntity(BodyExtractDto dto);
}
