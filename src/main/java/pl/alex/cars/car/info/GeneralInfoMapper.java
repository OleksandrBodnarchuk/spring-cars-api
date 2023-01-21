package pl.alex.cars.car.info;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GeneralInfoMapper {

	GeneralInfoMapper INSTANCE = Mappers.getMapper(GeneralInfoMapper.class);

}
