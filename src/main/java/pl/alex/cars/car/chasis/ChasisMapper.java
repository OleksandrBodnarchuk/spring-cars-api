package pl.alex.cars.car.chasis;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.extract.dto.ChasisExtractDto;

@Mapper
public interface ChasisMapper {

	ChasisMapper INSTANCE = Mappers.getMapper(ChasisMapper.class);
	
	Chasis convertToEntity(ChasisExtractDto dto);
}
