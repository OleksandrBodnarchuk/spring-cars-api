package pl.alex.cars.car.submodel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.car.brand.Brand;
import pl.alex.cars.extract.dto.BrandExtractDto;
import pl.alex.cars.extract.dto.SubModelExtractDto;

@Mapper
public interface SubModelMapper {
	SubModelMapper INSTANCE = Mappers.getMapper(SubModelMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "picture.id", ignore = true)
	@Mapping(target = "body.id", ignore = true)
	@Mapping(target = "chasis.id", ignore = true)
	@Mapping(target = "engine.id", ignore = true)
	@Mapping(target = "generalInfo.id", ignore = true)
	SubModel convertToEntity(SubModelExtractDto dto);

}
