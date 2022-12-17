package pl.alex.cars.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.alex.cars.dto.ManufacturerDto;
import pl.alex.cars.entity.Manufacturer;
import pl.alex.cars.mapper.ManufacturerMapper;
import pl.alex.cars.repository.ManufacturerRepository;

@Service
public class ManufacturerService {

	private ManufacturerRepository manufacturerRepository;

	public ManufacturerService(ManufacturerRepository manufacturerRepository) {
		this.manufacturerRepository = manufacturerRepository;
	}

	public boolean saveAllManufacturerDtos(List<ManufacturerDto> dtos) {

		List<Manufacturer> manufacturers = dtos.stream().map(ManufacturerMapper.INSTANCE::convertToEntity)
				.collect(Collectors.toList());
		manufacturerRepository.saveAll(manufacturers);
		return !manufacturers.isEmpty();
	}

}
