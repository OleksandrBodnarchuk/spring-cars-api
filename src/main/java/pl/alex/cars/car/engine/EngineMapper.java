package pl.alex.cars.car.engine;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.extract.dto.EngineExtractDto;

@Mapper
public interface EngineMapper {

	EngineMapper INSTANCE = Mappers.getMapper(EngineMapper.class);

	Engine convertToEntity(EngineExtractDto dto);
}
