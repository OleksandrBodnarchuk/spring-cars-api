package pl.alex.cars.car.file;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.alex.cars.car.brand.Brand;
import pl.alex.cars.car.brand.BrandDto;
import pl.alex.cars.car.brand.BrandService;

@Service
public class LogoService {

	@Value("url.main")
	private String MAIN_URL;
	
	LogoRepository logoRepository;
	BrandService manufacturerService;
	public LogoService(LogoRepository logoRepository) {
		this.logoRepository = logoRepository;
	}

	public Logo saveLogo(InputStream inputStream, BrandDto dto) throws IOException {
		Logo logo = new Logo();
		logo.setName(dto.getName());
		logo.setData(inputStream.readAllBytes());
		Logo saved = logoRepository.save(logo);
		return saved;
	}
	
//	public void getLogoPictures() {
//		List<ManufacturerDto> dtos = manufacturerService.findAllManufacturers();
//		String path = "/spring-cars-api/src/main/resources/logos";
//		dtos.forEach(dto -> {
//			downloadImagesFromUrl(path, dto, MAIN_URL + dto.getValue() + ".jpg");
//		});
//	}
//
//	private void downloadImagesFromUrl(String path, ManufacturerDto dto, String carUrl) {
//		// DOWNLOADING
//		try (InputStream in = new URL(carUrl).openStream()) {
//			Logo saved = saveLogo(in, dto);
//			manufacturerService.saveDtoWithLogo(dto, saved);
//		} catch (IOException ex) {
//			System.out.println(ex.getMessage());
//		}
//	}
	
}
