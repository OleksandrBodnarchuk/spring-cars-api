package pl.alex.cars.car.body;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.extract.dto.BodyExtractDto;
import pl.alex.cars.utils.CommonUtils;

@Mapper
public interface BodyMapper {

	BodyMapper INSTANCE = Mappers.getMapper(BodyMapper.class);

	@Mapping(target="id", ignore = true)
	@Mapping(source = "length", target = "length", qualifiedByName = "convertToInteger")
	@Mapping(source = "width", target = "width", qualifiedByName = "convertToInteger")
	@Mapping(source = "height", target = "height", qualifiedByName = "convertToInteger")
	@Mapping(source = "weight", target = "weight", qualifiedByName = "convertToInteger")
	@Mapping(source = "maxWidth", target = "maxWidth", qualifiedByName = "convertToInteger")
	Body convertToEntity(BodyExtractDto dto);

	@Named(value = "convertToInteger")
	public static Integer convertToInteger(String input) {
		return CommonUtils.convertFieldToInteger(input);
	}
}
