package pl.alex.cars.service;

import org.springframework.stereotype.Service;

import pl.alex.cars.repository.ManufacturerRepository;

@Service
public class ManufacturerService {

	private final ManufacturerRepository manufacturerRepository;

	public ManufacturerService(ManufacturerRepository manufacturerRepository) {
		this.manufacturerRepository = manufacturerRepository;
	}

//	public boolean saveAllManufacturerDtos(List<ManufacturerDto> dtos) {
//		List<Manufacturer> manufacturers = dtos.stream().map(ManufacturerMapper.INSTANCE::convertToEntity)
//				.collect(Collectors.toList());
//		manufacturerRepository.saveAll(manufacturers);
//		return !manufacturers.isEmpty();
//	}
//
//	public List<ManufacturerDto> findAllManufacturers() {
//		// TODO: add checks for null
//		return manufacturerRepository.findAll().stream().map(ManufacturerMapper.INSTANCE::convertToDto)
//				.collect(Collectors.toList());
//	}
//
//	public void saveDtoWithLogo(ManufacturerDto dto, Logo logo) {
//		if(dto!=null) {
//			Manufacturer entity = ManufacturerMapper.INSTANCE.convertToEntity(dto);
//			entity.setLogo(logo);
//			manufacturerRepository.save(entity);
//		}
//	}

}