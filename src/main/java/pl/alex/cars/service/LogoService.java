package pl.alex.cars.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import pl.alex.cars.entity.Logo;
import pl.alex.cars.entity.Manufacturer;
import pl.alex.cars.repository.LogoRepository;

@Service
public class LogoService {

	LogoRepository logoRepository;

	public LogoService(LogoRepository logoRepository) {
		this.logoRepository = logoRepository;
	}

	public Logo saveLogo(InputStream inputStream, Manufacturer entity) throws IOException {
		Logo logo = new Logo();
		logo.setName(entity.getName());
		logo.setData(inputStream.readAllBytes());
		Logo saved = logoRepository.save(logo);
		
		return saved;
	}
	
}
