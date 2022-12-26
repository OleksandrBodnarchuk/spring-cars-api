package pl.alex.cars.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.dto.extract.ModificationExctractDto;
import pl.alex.cars.entity.Modification;

@Mapper(uses = { BodyMapper.class, ChasisMapper.class, EngineMapper.class, GeneralInfoMapper.class,
		RunningFeaturesMapper.class })
public interface ModificationMapper {

	ModificationMapper INSTANCE = Mappers.getMapper(ModificationMapper.class);

	Modification convertToEntity(ModificationExctractDto dto);

}
