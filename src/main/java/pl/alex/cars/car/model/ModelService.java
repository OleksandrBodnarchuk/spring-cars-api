package pl.alex.cars.car.model;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

	private final ModelRepository modelRepository;

	public ModelService(ModelRepository modelRepository) {
		this.modelRepository = modelRepository;
	}

	public Page<ModelResponse> getModelResponseByBrandName(String name) {
		return null;
	}
	
}
