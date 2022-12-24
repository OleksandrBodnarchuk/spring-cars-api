package pl.alex.cars.dto.extract;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.dto.extract.details.Body;
import pl.alex.cars.dto.extract.details.Chasis;
import pl.alex.cars.dto.extract.details.Engine;
import pl.alex.cars.dto.extract.details.RunningFeature;

@Getter
@Setter
@Builder
public class ModificationExctractDto {
	private String imageLink;
	private String name;
	private String url;
	private GeneralInfo generalInfo;
	private Body body;
	private Engine engine;
	private Chasis chasis;
	private RunningFeature runningFeature;
}
