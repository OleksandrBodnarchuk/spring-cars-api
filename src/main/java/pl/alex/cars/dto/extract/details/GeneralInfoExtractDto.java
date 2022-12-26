package pl.alex.cars.dto.extract.details;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GeneralInfoExtractDto {
	private String year;
	private String brand; 
	private String engine;
	private String bodyType;
	private String model;
	private String doors;
	private String seats;
}
