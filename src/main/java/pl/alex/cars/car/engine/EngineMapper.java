package pl.alex.cars.car.engine;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.extract.dto.EngineExtractDto;
import pl.alex.cars.utils.CommonUtils;

@Mapper
public interface EngineMapper {

	EngineMapper INSTANCE = Mappers.getMapper(EngineMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "modification", ignore = true)
	@Mapping(source = "kw", target = "kw", qualifiedByName = "convertToInteger")
	@Mapping(source = "hp", target = "hp", qualifiedByName = "convertToInteger")
	@Mapping(source = "cylinders", target = "cylinders", qualifiedByName = "convertToInteger")
	@Mapping(source = "cylinderDiameter", target = "cylinderDiameter", qualifiedByName = "mapToDouble")
	@Mapping(source = "valvesInCylinders", target = "valvesInCylinders", qualifiedByName = "convertToInteger")
	@Mapping(source = "gears", target = "gears", qualifiedByName = "convertToInteger")
	@Mapping(source = "fuelCapacity", target = "fuelCapacity", qualifiedByName = "mapToDouble")
	Engine convertToEntity(EngineExtractDto dto);
	
	@Named(value = "convertToInteger")
	public static Integer convertToInteger(String input) {
		return CommonUtils.convertFieldToInteger(input);
	}
	
	@Named(value = "mapToDouble")
	public static Double mapToDouble(String input) {
		return CommonUtils.convertFieldToDouble(input);
	}
}
