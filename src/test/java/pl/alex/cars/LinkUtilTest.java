package pl.alex.cars;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import pl.alex.cars.dto.extract.BrandExtractDto;

public class LinkUtilTest {

	@Test
	void testGetManufacturerDetailsFromLink() {
		List<BrandExtractDto> dtos = new ArrayList<>();
		String line = "<a href=\"/en/models/ac\" class=\"list-group-item\">AC<span class=\"badge\">16</span></a>";
		String url = line.substring(line.indexOf("\"") + 1, line.indexOf(" class") - 1);
		assertTrue(url.equals("/en/models/ac"));
		String name = line.substring(line.indexOf(">") + 1, line.indexOf("<span"));
		assertTrue(name.equals("AC"));
		BrandExtractDto dto = BrandExtractDto.builder().name(name).url(url).build();
		dtos.add(dto);
		assertTrue(!dtos.isEmpty());
	}

	@Test
	void testGetModelrDetailsFromLink() {
		String line = "<a title=\"Marauder\" href=\"/en/models/mercury/marauder\">";
		String name = line.substring(line.indexOf("\"") + 1, line.indexOf("\" href=\""));
		assertTrue(name.equals("Marauder"));
		String link = line.substring(line.indexOf("/en/models/"), line.lastIndexOf("\""));
		assertTrue(link.equals("/en/models/mercury/marauder"));
	}

}
