package pl.alex.cars.car.info;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.extract.dto.GeneralInfoExtractDto;

@Mapper
public interface GeneralInfoMapper {

	GeneralInfoMapper INSTANCE = Mappers.getMapper(GeneralInfoMapper.class);

	GeneralInfo convertToEntity(GeneralInfoExtractDto dto);
}
