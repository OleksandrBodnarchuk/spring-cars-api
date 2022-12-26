package pl.alex.cars.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.dto.extract.BrandExtractDto;
import pl.alex.cars.dto.extract.ModelExtractDto;
import pl.alex.cars.entity.Brand;
import pl.alex.cars.entity.Model;

@Mapper
public interface BrandMapper {
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "models" , source = "models", qualifiedByName = "modelsDtoToList")
	Brand convertToEntity(BrandExtractDto dto);

	@Named("modelsDtoToList")
	public static List<Model> inchToCentimeter(List<ModelExtractDto> dtos) {
		List<Model> models = dtos.stream().map(ModelMapper.INSTANCE::convertToEntity).collect(Collectors.toList());
		return models;
	}
}