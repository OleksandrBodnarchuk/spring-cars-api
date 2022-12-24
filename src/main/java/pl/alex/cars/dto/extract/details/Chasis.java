package pl.alex.cars.dto.extract.details;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Chasis {
	private String abs;
	private String frontBrakes;
	private String rearBrakes;
	private String tires;
	private String wheels;
}
