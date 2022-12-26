package pl.alex.cars.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import pl.alex.cars.car.brand.Brand;
import pl.alex.cars.car.brand.BrandMapper;
import pl.alex.cars.car.brand.BrandRepository;
import pl.alex.cars.extract.dto.BrandExtractDto;
import pl.alex.cars.extract.dto.ModelExtractDto;
import pl.alex.cars.extract.dto.ModificationExctractDto;
import pl.alex.cars.extract.dto.SubModelExtractDto;

@ActiveProfiles("test")
@DataJpaTest
public class ManufacturerJpaTests {
//
//	@Nested
//	@DisplayName(value = "ManufacturerRepository tests")
//	class ManufacturerRepositoryTest {
//		@Autowired
//		ManufacturerRepository manufacturerRepository;
//		List<Manufacturer> manufacturers;
//		@BeforeEach
//		void setUp() {
//			Manufacturer manufacturer1 = new Manufacturer();
//			manufacturer1.setManufacturer_value(1L);
//			manufacturer1.setName("manufacturer1");
//			Manufacturer manufacturer2 = new Manufacturer();
//			manufacturer2.setManufacturer_value(2L);
//			manufacturer2.setName("manufacturer2");
//			Manufacturer manufacturer3 = new Manufacturer();
//			manufacturer3.setManufacturer_value(3L);
//			manufacturer3.setName("manufacturer3");
//			manufacturers = Arrays.asList(manufacturer1, manufacturer2, manufacturer3);
//		}
//		@Test
//		public void when_saveAll_thenListNotEmpty() {
//			manufacturerRepository.saveAll(manufacturers);
//			assertThat(manufacturerRepository.count()).isEqualTo(3L);
//		}
//		
//		@Test
//		public void when_getAll_thenListNotEmpty() {
//			manufacturerRepository.saveAll(manufacturers);
//			List<Manufacturer> all = manufacturerRepository.findAll();
//			assertThat(all.size()).isEqualTo(3);
//		}
//		
//	}
	
	@Autowired 
	BrandRepository brandRepository;
	
	@Test
	void testSaveBrandFully(){
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
		Brand saved = brandRepository.save(brand);
		
		assertThat(saved).isNotNull();
		assertThat(saved.getName()).isEqualTo(name);
//		assertThat(brand.getModels()).isNotEmpty();
//		assertThat(brand.getModels().get(0).getName()).isEqualTo(modelName);
//		assertThat(brand.getModels().get(0).getSubmodels()).isNotEmpty();
	}
}
