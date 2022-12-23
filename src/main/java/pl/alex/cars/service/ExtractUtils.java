package pl.alex.cars.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.alex.cars.dto.extract.DtoPairs;
import pl.alex.cars.dto.extract.Extractable;
import pl.alex.cars.dto.extract.ManufacturerExtractDto;
import pl.alex.cars.dto.extract.ModelExtractDto;
import pl.alex.cars.dto.extract.ModificationExctractDto;
import pl.alex.cars.dto.extract.SubModelExtractDto;

public class ExtractUtils {

	Logger logger = LoggerFactory.getLogger(ExtractUtils.class);

	private String mainUrl = "https://car-info.com";

	public void extractAll() {
		logger.debug("[ExtractService] - extractAll");
		Map<String, ManufacturerExtractDto> dtos = extractInitialData();
		dtos.forEach((name, dto) -> {
			dto = extractData(dto).getManufacturer();
			dto.getModels().forEach(model -> {
				model = extractData(model).getModel();
				model.getSubModels().forEach(subModel -> {
					subModel = extractSubmodelData(subModel);
					subModel.getModifications().forEach(modification -> {
						modification = extractModificationData(modification);
					});
				});
			});
			System.out.println(dto);			
		});
						
	}

	private ModificationExctractDto extractModificationData(ModificationExctractDto dto) {
		logger.debug("[ExtractService] - extractSubmodelData");
		String line;
		String name="";
		String link="";
		try (BufferedReader dis = new BufferedReader(new InputStreamReader(new URL(dto.getUrl()).openStream()))) {
			while ((line = dis.readLine()) != null) {
				// General information
				// Body Features
				// Engine Transmission
				// Chassis
				// Running Features
				System.out.println(line);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return dto;
	}

	private SubModelExtractDto extractSubmodelData(SubModelExtractDto dto) {
		logger.debug("[ExtractService] - extractSubmodelData");
		String line;
		String name="";
		String link="";
		try (BufferedReader dis = new BufferedReader(new InputStreamReader(new URL(dto.getUrl()).openStream()))) {
			while ((line = dis.readLine()) != null) {
				line = line.trim();
				if (line.trim().startsWith("<a href=\"/en/model/")) {
					link = line.substring(line.indexOf("\"") + 1, line.indexOf("\">"));
					if ((line = dis.readLine()) != null) {
						name = line.trim();
					}
					ModificationExctractDto modificationDto = ModificationExctractDto
															  .builder()
															  .name(name)
															  .url(mainUrl + link)
															  .build();
					if (dto.getModifications() == null) {
						dto.setModifications(new ArrayList<>());
					}
					dto.getModifications().add(modificationDto);
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return dto;
	}

	private DtoPairs extractData(Extractable dto) {
		logger.debug("[ExtractService] - extractData");
		String line;
		DtoPairs pair = null;
		try (BufferedReader dis = new BufferedReader(new InputStreamReader(new URL(dto.getUrl()).openStream()))) {
			while ((line = dis.readLine()) != null) {
				line = line.trim();
				if (line.trim().startsWith("<a title=\"")) {
					String name = line.substring(line.indexOf("\"") + 1, line.indexOf("\" href=\""));
					String link = line.substring(line.indexOf("/en/models/"), line.lastIndexOf("\""));
					if (dto instanceof ManufacturerExtractDto) {
						pair= new DtoPairs(fillManufacturerData((ManufacturerExtractDto) dto, name, link), null);	 
					} else if (dto instanceof ModelExtractDto) {
						pair = new DtoPairs(null, fillModelData((ModelExtractDto) dto, name, link));
					}
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return pair;
	}

	private ModelExtractDto fillModelData(ModelExtractDto dto, String name, String link) {
		SubModelExtractDto variant = SubModelExtractDto
				.builder()
				.name(name)
				.url(mainUrl + link)
				.modifications(null)
				.build();
		if (dto.getSubModels() == null) {
			dto.setSubModels(new ArrayList<>());
		}
		dto.getSubModels().add(variant);
		return dto;
	}

	private ManufacturerExtractDto fillManufacturerData(ManufacturerExtractDto dto, String name, String link) {
		ModelExtractDto modelDto = ModelExtractDto
				.builder()
				.name(name)
				.url(mainUrl + link)
				.subModels(null)
				.build();
		if (dto.getModels() == null) {
			dto.setModels(new ArrayList<>());
		}
		dto.getModels().add(modelDto);

		return dto;
	}

	private Map<String, ManufacturerExtractDto> extractInitialData() {
		logger.debug("[ExtractService] - extractManufacturersData");
		String line;
		Map<String, ManufacturerExtractDto> dtoMap = new HashMap<>();
		try (BufferedReader dis = new BufferedReader(new InputStreamReader(new URL(mainUrl).openStream()))) {
			while ((line = dis.readLine()) != null) {
				if (line.trim().startsWith("<a href=\"/en/models/")) {
					String url = line.substring(line.indexOf("\"") + 1, line.indexOf(" class") - 1);
					String name = line.substring(line.indexOf(">") + 1, line.indexOf("<span"));
					ManufacturerExtractDto dto = ManufacturerExtractDto.builder().name(name)
							.url(mainUrl + url).build();
					dtoMap.put(name, dto);
				}
			}
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return dtoMap;
	}

}
