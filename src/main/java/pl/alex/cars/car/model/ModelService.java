package pl.alex.cars.car.model;

import org.springframework.stereotype.Service;

@Service
public class ModelService {

	private final ModelRepository modelRepository;

	public ModelService(ModelRepository modelRepository) {
		this.modelRepository = modelRepository;
	}

	public void getModels() {
		// TODO Auto-generated method stub
		
	}
	
	
}
