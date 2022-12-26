package pl.alex.cars.dto.extract;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.dto.extract.details.BodyExtractDto;
import pl.alex.cars.dto.extract.details.ChasisExtractDto;
import pl.alex.cars.dto.extract.details.EngineExtractDto;
import pl.alex.cars.dto.extract.details.RunningFeatureExtractDto;

@Getter
@Setter
@Builder
public class ModificationExctractDto {
	private String imageLink;
	private String name;
	private String url;
	private GeneralInfo generalInfo;
	private BodyExtractDto body;
	private EngineExtractDto engine;
	private ChasisExtractDto chasis;
	private RunningFeatureExtractDto runningFeature;
}
