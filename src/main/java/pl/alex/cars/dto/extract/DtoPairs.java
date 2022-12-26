package pl.alex.cars.dto.extract;

import lombok.Getter;

@Getter
public class DtoPairs {
	
	public final BrandExtractDto brand;
	public final ModelExtractDto model;
	
	public DtoPairs(BrandExtractDto brand, ModelExtractDto model) {
		this.brand = brand;
		this.model = model;
	}
	
}
