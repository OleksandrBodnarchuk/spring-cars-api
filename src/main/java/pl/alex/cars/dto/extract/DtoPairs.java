package pl.alex.cars.dto.extract;

import lombok.Getter;

@Getter
public class DtoPairs {
	
	public final ManufacturerExtractDto manufacturer;
	public final ModelExtractDto model;
	
	public <T extends Extractable, C extends Extractable> DtoPairs(T manufacturer, C model) {
		this.manufacturer = (ManufacturerExtractDto) manufacturer;
		this.model = (ModelExtractDto) model;
	}
}
