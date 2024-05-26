package pl.alex.cars.api.info;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GeneralInfoMapper {

	GeneralInfoMapper INSTANCE = Mappers.getMapper(GeneralInfoMapper.class);

}
