package pl.alex.cars.dto.extract;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GeneralInfo {
	private String year;
	private String brand; 
	private String engine;
	private String bodyType;
	private String model;
	private String doors;
	private String seats;
}
