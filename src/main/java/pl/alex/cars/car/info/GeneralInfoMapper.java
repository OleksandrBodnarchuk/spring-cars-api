package pl.alex.cars.car.info;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.extract.dto.GeneralInfoExtractDto;

@Mapper
public interface GeneralInfoMapper {

	GeneralInfoMapper INSTANCE = Mappers.getMapper(GeneralInfoMapper.class);

	@Mapping(source=  "year", target="yearOfCreation")
	GeneralInfo convertToEntity(GeneralInfoExtractDto dto);
}
