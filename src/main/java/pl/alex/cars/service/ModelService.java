package pl.alex.cars.service;

import org.springframework.stereotype.Service;

import pl.alex.cars.repository.ModelRepository;

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
