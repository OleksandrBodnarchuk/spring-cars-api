package pl.alex.cars.api.brand.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.alex.cars.api.brand.dto.BrandRequest;
import pl.alex.cars.api.brand.dto.BrandResponse;
import pl.alex.cars.api.brand.entity.Brand;
import pl.alex.cars.api.brand.repository.BrandRepository;
import pl.alex.cars.api.model.entity.Model;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

	private List<Brand> brands;
	private List<Model> models;

	@Mock
	BrandRepository brandRepository;
	@InjectMocks
	BrandService underTest;

	@BeforeEach
	public void setUp() {
		brands = fillUpBrands();
		models = fillUpModels();
	}

	@DisplayName("[findAllBrands] - should return Page<BrandResponse>")
	@Test
	public void when_findAllBrands_thenReturnPageOfBrandResponse() {
		given(brandRepository.findAll()).willReturn(brands);
		List<String> findAllBrands = underTest.findAllBrands();

		assertThat(findAllBrands.size()).isEqualTo(20);
		assertThat(findAllBrands.get(5)).contains(brands.get(5).getName());
	}

	@DisplayName("[getBrandResponseByName] - should return BrandResponse")
	@Test
	public void when_getBrandResponseByName_thenReturnBrandResponse() {
		String name = brands.get(5).getName();
		given(brandRepository.findByNameEquals(name)).willReturn(Optional.of(brands.get(5)));

		BrandResponse response = underTest.getBrandResponseByName(name);

		assertTrue(!response.getModels().isEmpty());
		assertTrue(response.getModels().size() == 3);
		assertTrue(response.getModels().contains(models.get(0).getName()));

	}

	@DisplayName("[getBrandResponseByName]- when brand is not found - should throw exception")
	@Test
	public void when_getBrandResponseBy_Wrong_Name_thenThrowException() {
		String name = "Bob";

		given(brandRepository.findByNameEquals(name)).willReturn(Optional.empty());

		assertThatThrownBy(() -> underTest.getBrandResponseByName(name))
        	.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("[getMultipleBrands]- with multiple brands - should return list")
	@Test
	public void when_getMultipleBrands_shouldReturnList() {
		BrandRequest brandRequest = new BrandRequest();
		brandRequest.setNames(List.of("Brand1", "Brand2"));

		given(brandRepository.findBrandByNameIn(brandRequest.getNames())).willReturn(brands.subList(0, 2));

		List<String> response = underTest.getMultipleBrands(brandRequest);

		assertThat(response.size()).isEqualTo(2);
		assertThat(response.get(0)).isNotEmpty();
		assertThat(response.get(0)).contains(brands.get(0).getName());

	}


	private List<Brand> fillUpBrands() {
		List<Brand> result = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			Brand brand = new Brand();
			brand.setName("[" + i + "] " + "Brand");
			result.add(brand);
		}
		return result;
	}

	private List<Model> fillUpModels() {
		List<Model> result = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Model model = new Model();
			model.setBrand(brands.get(5));
			model.setName("MODEL [" + i + "]");
			result.add(model);
		}
		brands.get(0).setModels(result);
		brands.get(1).setModels(result);
		brands.get(5).setModels(result);
		return result;
	}
}
