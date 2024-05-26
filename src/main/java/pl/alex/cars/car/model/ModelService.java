package pl.alex.cars.car.model;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.alex.cars.api.ui.dto.ModelRequestQuery;

@Service
public class ModelService {

	private final ModelRepository modelRepository;

	public ModelService(ModelRepository modelRepository) {
		this.modelRepository = modelRepository;
	}

	public Page<ModelResponse> getModelResponseByBrandName(ModelRequestQuery modelRequestQuery) {
		return modelRepository.findAllByBrandName(modelRequestQuery.brandName(), modelRequestQuery.pageable());
	}

	public Page<ModelResponse> findAllModels(ModelRequest modelRequest) {
		return null;
	}
	
}
