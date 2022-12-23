package pl.alex.cars.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.alex.cars.dto.extract.ManufacturerExtractDto;
import pl.alex.cars.dto.extract.ModelExtractDto;

public class ExtractUtils {

	Logger logger = LoggerFactory.getLogger(ExtractUtils.class);

	private String mainUrl = "https://car-info.com";

	public void extractAll() {
		logger.debug("[ExtractService] - extractAll");
		Map<String, ManufacturerExtractDto> dtos = extractManufacturersData();
		dtos.forEach((name, dto) -> {
			dto = extractManufacturerDetailsData(dto);
		});
						
	}

	private ManufacturerExtractDto extractManufacturerDetailsData(ManufacturerExtractDto dto) {
		logger.debug("[ExtractService] - extractManufacturerDetailsData");
		String line;
		try (BufferedReader dis = new BufferedReader(new InputStreamReader(new URL(dto.getUrl()).openStream()))) {
			while ((line = dis.readLine()) != null) {
				if (line.trim().startsWith("<a title=\"")) {
					String name = line.substring(line.indexOf("\"") + 1, line.indexOf("\" href=\""));
					String link = line.substring(line.indexOf("/en/models/"), line.lastIndexOf("\""));
					ModelExtractDto modelDto = ModelExtractDto
							.builder()
							.name(name)
							.url(link)
							.variants(null)
							.build();
					if (dto.getModels() == null) {
						dto.setModels(new ArrayList<>());
					}
					dto.getModels().add(modelDto);
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return dto;
	}

	private Map<String, ManufacturerExtractDto> extractManufacturersData() {
		logger.debug("[ExtractService] - extractManufacturersData");
		String line;
		Map<String, ManufacturerExtractDto> dtoMap = new HashMap<>();
		try (BufferedReader dis = new BufferedReader(new InputStreamReader(new URL(mainUrl).openStream()))) {
			while ((line = dis.readLine()) != null) {
				if (line.trim().startsWith("<a href=\"/en/models/")) {
					String url = this.mainUrl + line.substring(line.indexOf("\"") + 1, line.indexOf(" class") - 1);
					String name = line.substring(line.indexOf(">") + 1, line.indexOf("<span"));
					ManufacturerExtractDto dto = ManufacturerExtractDto.builder().name(name).url(url).build();
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
