package pl.alex.cars.extract.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ModificationExctractDto {
	private String imageLink;
	private String name;
	private String url;
	private GeneralInfoExtractDto generalInfo;
	private BodyExtractDto body;
	private EngineExtractDto engine;
	private ChasisExtractDto chasis;
	private RunningFeatureExtractDto runningFeature;
}
