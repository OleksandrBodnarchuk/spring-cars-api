package pl.alex.cars.car.running;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.extract.dto.RunningFeatureExtractDto;
import pl.alex.cars.utils.CommonUtils;

@Mapper
public interface RunningFeaturesMapper {
	RunningFeaturesMapper INSTANCE = Mappers.getMapper(RunningFeaturesMapper.class);
	
	@Mapping(source="maxSpeed",target="maxSpeed", qualifiedByName = "convertToDouble")
	@Mapping(source="acceleration",target="acceleration", qualifiedByName = "convertToDouble")
	@Mapping(source="fuelTown",target="fuelTown", qualifiedByName = "convertToDouble")
	@Mapping(source="fuelRoad",target="fuelRoad", qualifiedByName = "convertToDouble")
	@Mapping(source="fuelAverage",target="fuelAverage", qualifiedByName = "convertToDouble")
	RunningFeature convertToEntity(RunningFeatureExtractDto dto);
	
	@Named(value = "convertToInteger")
	public static Integer convertToInteger(String input) {
		return CommonUtils.convertFieldToInteger(input);
	}

	@Named(value = "convertToDouble")
	public static Double convertToDouble(String input) {
		return CommonUtils.convertFieldToDouble(input);
	}
}
