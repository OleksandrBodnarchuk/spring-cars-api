package pl.alex.cars.api.modification.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.alex.cars.api.modification.dto.ModificationResponse;

@Service
public class ModificationService {

	public Page<ModificationResponse> getSubModelResponseByBrandName(String modelName) {
		return null;
	}

}
