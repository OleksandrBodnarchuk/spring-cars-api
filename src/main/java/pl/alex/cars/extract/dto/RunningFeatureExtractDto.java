package pl.alex.cars.extract.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RunningFeatureExtractDto {

	private String maxSpeed;
	private String acceleration;
	private String fuelTown;
	private String fuelRoad;
	private String fuelAverage;
}
