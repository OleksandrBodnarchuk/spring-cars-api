package pl.alex.cars.api.model.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.alex.cars.api.model.dto.ModelRequest;
import pl.alex.cars.api.model.dto.ModelRequestQuery;
import pl.alex.cars.api.model.dto.ModelResponse;
import pl.alex.cars.api.model.entity.Model;
import pl.alex.cars.api.model.repository.ModelRepository;

@Service
@RequiredArgsConstructor
public class ModelService {

  private final ModelRepository modelRepository;

  public Page<ModelResponse> getModelResponseByBrandName(ModelRequestQuery modelRequestQuery) {
    return modelRepository.findAllByBrandName(modelRequestQuery.brandName(),
        modelRequestQuery.pageable());
  }

  public List<String> getModelsByBrandName(String brandName) {
    return modelRepository.findAllByBrandName(brandName)
        .stream()
        .filter(Objects::nonNull).map(
        Model::getName)
        .collect(Collectors.toList());
  }

  public Page<ModelResponse> findAllModels(ModelRequest modelRequest) {
    return null;
  }

}
