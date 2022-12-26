package pl.alex.cars.car.modification;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.car.body.BodyMapper;
import pl.alex.cars.car.chasis.ChasisMapper;
import pl.alex.cars.car.engine.EngineMapper;
import pl.alex.cars.car.info.GeneralInfoMapper;
import pl.alex.cars.car.running.RunningFeaturesMapper;
import pl.alex.cars.extract.dto.ModificationExctractDto;

@Mapper(uses = { BodyMapper.class, ChasisMapper.class, EngineMapper.class, GeneralInfoMapper.class,
		RunningFeaturesMapper.class })
public interface ModificationMapper {

	ModificationMapper INSTANCE = Mappers.getMapper(ModificationMapper.class);

	Modification convertToEntity(ModificationExctractDto dto);

}
