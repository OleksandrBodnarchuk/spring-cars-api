package pl.alex.cars.api.model;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
