package pl.alex.cars.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.dto.extract.details.GeneralInfoExtractDto;
import pl.alex.cars.entity.details.GeneralInfo;

@Mapper
public interface GeneralInfoMapper {

	GeneralInfoMapper INSTANCE = Mappers.getMapper(GeneralInfoMapper.class);

	GeneralInfo convertToEntity(GeneralInfoExtractDto dto);
}
