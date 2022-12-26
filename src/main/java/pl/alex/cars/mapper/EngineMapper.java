package pl.alex.cars.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.dto.extract.details.EngineExtractDto;
import pl.alex.cars.entity.details.Engine;

@Mapper
public interface EngineMapper {

	EngineMapper INSTANCE = Mappers.getMapper(EngineMapper.class);

	Engine convertToEntity(EngineExtractDto dto);
}
