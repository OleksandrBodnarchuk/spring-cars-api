package pl.alex.cars.car.modification;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.extract.dto.ModificationExctractDto;

@Mapper
public interface ModificationMapper {

	ModificationMapper INSTANCE = Mappers.getMapper(ModificationMapper.class);
	
	@Mapping(target="id" , ignore = true)
	@Mapping(target="picture" , ignore = true)
	@Mapping(target="generalInfo" , ignore = true)
	@Mapping(target="body" , ignore = true)
	@Mapping(target="engine" , ignore = true)
	@Mapping(target="chasis" , ignore = true)
	@Mapping(target="runningFeature" , ignore = true)
	Modification convertToEntity(ModificationExctractDto dto);

}
