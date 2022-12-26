package pl.alex.cars.car.running;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.extract.dto.RunningFeatureExtractDto;

@Mapper
public interface RunningFeaturesMapper {
	RunningFeaturesMapper INSTANCE = Mappers.getMapper(RunningFeaturesMapper.class);
	
	RunningFeature convertToEntity(RunningFeatureExtractDto dto);
}
