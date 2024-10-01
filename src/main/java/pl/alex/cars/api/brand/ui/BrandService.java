package pl.alex.cars.api.brand.ui;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.alex.cars.api.brand.data.Brand;
import pl.alex.cars.api.brand.exception.BrandNotFoundException;
import pl.alex.cars.api.brand.data.BrandRepository;
import pl.alex.cars.api.model.dto.ModelResponse;

@Service
@RequiredArgsConstructor
class BrandService {

  private final BrandRepository brandRepository;

  public List<BrandResponse> findAllBrands() {
    return brandRepository.findAll()
        .stream()
        .map(brand -> BrandResponse.of(brand.getId(), brand.getName(),
            brand.getModels().stream()
                .filter(Objects::nonNull)
                .map(model -> new ModelResponse(model.getId(), model.getName()))
                .collect(Collectors.toList())))
        .collect(Collectors.toList());
  }

  public BrandResponse getBrandResponseByName(String name, boolean includeModels) {
    Brand brand = brandRepository.findByNameEquals(name)
        .orElseThrow(() -> new BrandNotFoundException("Brand with name [" + name + "] Not found."));

    List<ModelResponse> brandModels = includeModels ?
        brand.getModels().stream()
            .filter(Objects::nonNull)
            .map(model -> new ModelResponse(model.getId(), model.getName()))
            .collect(Collectors.toList()) :
        List.of();

    return BrandResponse.of(brand.getId(), brand.getName(), brandModels);
  }

  public List<BrandResponse> getMultipleBrands(BrandRequest brandRequest) {
    List<Brand> brands = brandRepository.findBrandByNameIn(brandRequest.getNames());
    return brands.stream()
        .map(brand -> BrandResponse.of(brand.getId(), brand.getName(), brand.getModels()
            .stream()
            .filter(Objects::nonNull)
            .map(model -> new ModelResponse(model.getId(), model.getName()))
            .collect(Collectors.toList())))
        .collect(Collectors.toList());
  }
}
