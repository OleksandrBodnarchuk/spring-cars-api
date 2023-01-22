package pl.alex.cars.car.brand;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import pl.alex.cars.car.model.Model;
import pl.alex.cars.utils.CommonUtils;

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
		BrandRequest brandRequest = new BrandRequest();
		brandRequest.setPageNumber(0);
		brandRequest.setPageSize(20);
		Pageable pageable = CommonUtils.createPageable(brandRequest);
		given(brandRepository.findAll(pageable)).willReturn(new PageImpl<>(brands));

		Page<BrandResponse> findAllBrands = underTest.findAllBrands(brandRequest);

		assertThat(findAllBrands.getSize()).isEqualTo(20);
		assertThat(findAllBrands.toList().get(5).getName()).isEqualTo(brands.get(5).getName());
	}

	@DisplayName("[getBrandResponseByName] - should return BrandResponse")
	@Test
	public void when_getBrandResponseByName_thenReturnBrandResponse() {
		String name = brands.get(5).getName();
		given(brandRepository.findByNameEquals(name)).willReturn(Optional.of(brands.get(5)));

		BrandResponse response = underTest.getBrandResponseByName(name);

		assertTrue(!response.getModels().isEmpty());
		assertTrue(response.getModels().size() == 3);
		assertTrue(response.getModels().get(0).getName().equals(models.get(0).getName()));

	}

	@DisplayName("[getBrandResponseByName]- when brand is not found - should throw exception")
	@Test
	public void when_getBrandResponseBy_Wrong_Name_thenThrowException() {
		String name = "Bob";

		given(brandRepository.findByNameEquals(name)).willReturn(Optional.ofNullable(null));
		
		assertThatThrownBy(() -> underTest.getBrandResponseByName(name))
        	.isInstanceOf(IllegalArgumentException.class);
	}
	
	@DisplayName("[getMultipleBrands]- with multiple brands - should return list")
	@Test
	public void when_getMultipleBrands_shouldReturnList() {
		BrandRequest brandRequest = new BrandRequest();
		brandRequest.setNames(List.of("Brand1", "Brand2"));

		given(brandRepository.findBrandByNameIn(brandRequest.getNames())).willReturn(brands.subList(0, 2));
		
		List<BrandResponse> response = underTest.getMultipleBrands(brandRequest);
		
		assertThat(response.size()).isEqualTo(2);
		assertThat(response.get(0).getName()).isEqualTo(brands.get(0).getName());
		assertThat(response.get(0).getModels()).isNotEmpty();
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
