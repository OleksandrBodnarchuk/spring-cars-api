package pl.alex.cars.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.alex.cars.dto.ManufacturerDto;
import pl.alex.cars.entity.Logo;
import pl.alex.cars.entity.Manufacturer;
import pl.alex.cars.mapper.ManufacturerMapper;
import pl.alex.cars.repository.ManufacturerRepository;

@Service
public class ManufacturerService {

	private final ManufacturerRepository manufacturerRepository;
	private final LogoService logoService;

	public ManufacturerService(ManufacturerRepository manufacturerRepository, LogoService logoService) {
		this.manufacturerRepository = manufacturerRepository;
		this.logoService = logoService;
	}

	public boolean saveAllManufacturerDtos(List<ManufacturerDto> dtos) {
		List<Manufacturer> manufacturers = dtos.stream().map(ManufacturerMapper.INSTANCE::convertToEntity)
				.collect(Collectors.toList());
		manufacturerRepository.saveAll(manufacturers);
		return !manufacturers.isEmpty();
	}

	private List<Manufacturer> findAllManufacturers() {
		return manufacturerRepository.findAll();
	}

	public void getPicturest() {
		List<Manufacturer> entities = findAllManufacturers();
		String path = "/spring-cars-api/src/main/resources/logos";
		entities.forEach(e -> {
			downloadImagesFromUrl(path, e, MAIN_URL + e.getManufacturer_value() + ".jpg");
		});
	}

	protected static String MAIN_URL = "https://carmaniac.co.uk/images/makeslogos/";

	public void downloadImagesFromUrl(String path, Manufacturer entity, String carUrl) {
		// DOWNLOADING
		try (InputStream in = new URL(carUrl).openStream()) {
			Logo saved = logoService.saveLogo(in, entity);
			entity.setLogo(saved);
			manufacturerRepository.save(entity);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

	}
}