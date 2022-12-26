package pl.alex.cars.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.dto.extract.ModelExtractDto;
import pl.alex.cars.dto.extract.SubModelExtractDto;
import pl.alex.cars.entity.Model;
import pl.alex.cars.entity.SubModel;

@Mapper
public interface ModelMapper {
	ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "submodels" , source = "submodels", qualifiedByName = "submodelsDtoToList")
	@Mapping(target = "brand", ignore = true)
	Model convertToEntity(ModelExtractDto dto);
	
	@Named("submodelsDtoToList")
	public static List<SubModel> inchToCentimeter(List<SubModelExtractDto> dtos) {
		List<SubModel> submodels = dtos.stream().map(SubModelMapper.INSTANCE::convertToEntity).collect(Collectors.toList());
		return submodels;
	}
}
