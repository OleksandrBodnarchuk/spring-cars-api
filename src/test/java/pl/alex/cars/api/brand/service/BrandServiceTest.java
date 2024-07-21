package pl.alex.cars.api.brand.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.alex.cars.api.TestEntityFactory;
import pl.alex.cars.api.brand.dto.BrandRequest;
import pl.alex.cars.api.brand.dto.BrandResponse;
import pl.alex.cars.api.brand.entity.Brand;
import pl.alex.cars.api.brand.exception.BrandNotFoundException;
import pl.alex.cars.api.brand.repository.BrandRepository;
import pl.alex.cars.api.model.dto.ModelResponse;
import pl.alex.cars.api.model.entity.Model;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

  private static final List<Brand> BRANDS = TestEntityFactory.createBrandsWithModels(5, 3);

  @Mock
  BrandRepository brandRepository;

  @InjectMocks
  BrandService underTest;

  @DisplayName("[findAllBrands] - should return List<BrandResponse>")
  @Test
  public void when_findAllBrands_thenReturnListOfBrandResponse() {
    // Given
    given(brandRepository.findAll()).willReturn(BRANDS);

    // When
    List<BrandResponse> findAllBrands = underTest.findAllBrands();

    // Then
    assertThat(findAllBrands).hasSize(BRANDS.size());
    assertThat(findAllBrands).extracting("name").containsExactlyElementsOf(
        BRANDS.stream().map(Brand::getName).toList());
  }

  @DisplayName("[getBrandResponseByName] - should return BrandResponse with models")
  @Test
  public void when_getBrandResponseByNameWithModels_thenReturnBrandResponse() {
    // Given
    Brand brand = BRANDS.get(0);
    String name = brand.getName();
    given(brandRepository.findByNameEquals(name)).willReturn(Optional.of(brand));

    // When
    BrandResponse response = underTest.getBrandResponseByName(name, true);

    // Then
    assertThat(response.getModels()).isNotEmpty();
    assertThat(response.getModels()).hasSize(brand.getModels().size());
    assertThat(response.getModels().stream().map(ModelResponse::name)).containsExactlyElementsOf(
        brand.getModels().stream().map(Model::getName).toList());
  }

  @DisplayName("[getBrandResponseByName] - should return BrandResponse without models")
  @Test
  public void when_getBrandResponseByNameWithoutModels_thenReturnBrandResponse() {
    // Given
    Brand brand = BRANDS.get(0);
    String name = brand.getName();
    given(brandRepository.findByNameEquals(name)).willReturn(Optional.of(brand));

    // When
    BrandResponse response = underTest.getBrandResponseByName(name, false);

    // Then
    assertThat(response.getModels()).isEmpty();
  }

  @DisplayName("[getBrandResponseByName] - when brand is not found - should throw exception")
  @Test
  public void when_getBrandResponseByWrongName_thenThrowException() {
    // Given
    String name = "NonExistingBrand";
    given(brandRepository.findByNameEquals(name)).willReturn(Optional.empty());

    // When / Then
    assertThatThrownBy(() -> underTest.getBrandResponseByName(name, true))
        .isInstanceOf(BrandNotFoundException.class)
        .hasMessageContaining(name);
  }

  @DisplayName("[getMultipleBrands] - with multiple brands - should return list")
  @Test
  public void when_getMultipleBrands_shouldReturnList() {
    // Given
    BrandRequest brandRequest = new BrandRequest();
    brandRequest.setNames(BRANDS.stream().limit(2).map(Brand::getName).toList());

    given(brandRepository.findBrandByNameIn(brandRequest.getNames())).willReturn(
        BRANDS.subList(0, 2));

    // When
    List<BrandResponse> response = underTest.getMultipleBrands(brandRequest);

    // Then
    assertThat(response).hasSize(2);
    assertThat(response).extracting("name").containsExactlyElementsOf(
        BRANDS.subList(0, 2).stream().map(Brand::getName).toList());
  }
}
