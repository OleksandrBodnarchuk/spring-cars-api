package pl.alex.cars.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.dto.extract.details.RunningFeatureExtractDto;
import pl.alex.cars.entity.details.RunningFeature;

@Mapper
public interface RunningFeaturesMapper {
	RunningFeaturesMapper INSTANCE = Mappers.getMapper(RunningFeaturesMapper.class);
	
	RunningFeature convertToEntity(RunningFeatureExtractDto dto);
}
