package pl.alex.cars.extract.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.extract.Extractable;

@Getter
@Setter
@Builder
public class ModelExtractDto extends Extractable {

	private String name;
	private String url;
	private List<SubModelExtractDto> submodels;
}
