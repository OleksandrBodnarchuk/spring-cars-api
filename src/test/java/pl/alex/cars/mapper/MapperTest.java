package pl.alex.cars.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import pl.alex.cars.dto.ManufacturerDto;
import pl.alex.cars.entity.Manufacturer;

class MapperTest {

	@Test
	void shouldMapManufacturerDtoToEntity() {
		// given
		ManufacturerDto dto = new ManufacturerDto();
		dto.setManufacturer_value(23L);
		dto.setName("TEST");

		// when
		Manufacturer manufacturer = ManufacturerMapper.INSTANCE.convertToEntity(dto);

		// then
		assertThat(manufacturer).isNotNull();
		assertThat(manufacturer.getManufacturer_value()).isEqualTo(23L);
		assertThat(manufacturer.getName()).isEqualTo("TEST");
	}

}
