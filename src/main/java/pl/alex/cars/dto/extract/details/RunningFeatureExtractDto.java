package pl.alex.cars.dto.extract.details;

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
