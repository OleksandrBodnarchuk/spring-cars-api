package pl.alex.cars.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import pl.alex.cars.car.brand.Brand;
import pl.alex.cars.car.brand.BrandMapper;
import pl.alex.cars.car.brand.BrandMapperImpl;
import pl.alex.cars.extract.dto.BrandExtractDto;
import pl.alex.cars.extract.dto.ModelExtractDto;
import pl.alex.cars.extract.dto.ModificationExctractDto;
import pl.alex.cars.extract.dto.SubModelExtractDto;

@ExtendWith(SpringExtension.class) // JUnit 5
@ContextConfiguration(classes = { BrandMapperImpl.class })
class MapperTest {
	void shouldMapBrandDtoToEntity() {
		// given
		String name = "Brand";
		String url = "some URL";
		String modelName = "Model";
		String subModelName = "SubModel name";
		ModificationExctractDto modification = ModificationExctractDto.builder().body(null).chasis(null).engine(null)
				.generalInfo(null).imageLink(url).name("Modification Name").build();

		SubModelExtractDto subModel = SubModelExtractDto.builder().url(url).name(subModelName)
				.modifications(Arrays.asList(modification)).build();
		ModelExtractDto model = ModelExtractDto.builder().name(modelName).url(url)
				.submodels(Arrays.asList(subModel))
				.build();
		BrandExtractDto dto = BrandExtractDto.builder().name(name).url(url)
				.models(Arrays.asList(model)).build();

		// when
		Brand brand = BrandMapper.INSTANCE.convertToEntity(dto);

		// then
		assertThat(brand).isNotNull();
		assertThat(brand.getName()).isEqualTo(name);
		assertThat(brand.getModels()).isNotEmpty();
		assertThat(brand.getModels().get(0).getName()).isEqualTo(modelName);
		assertThat(brand.getModels().get(0).getSubmodels()).isNotEmpty();
//		assertThat(brand.getModels().get(0).getSubmodels()).isNotEmpty();
	}
}
