package pl.alex.cars.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import pl.alex.cars.dto.ModelDto;
import pl.alex.cars.entity.model.Model;

@Mapper
public interface ModelMapper {
	ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

	Model convertToEntity(ModelDto dto);
	ModelDto convertToDto(Model model);
}
