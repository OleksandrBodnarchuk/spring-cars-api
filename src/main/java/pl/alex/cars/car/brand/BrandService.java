package pl.alex.cars.car.brand;

import org.springframework.stereotype.Service;

@Service
public class BrandService {

	private final BrandRepository manufacturerRepository;

	public BrandService(BrandRepository manufacturerRepository) {
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