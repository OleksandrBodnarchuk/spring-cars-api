package pl.alex.cars.extract.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.alex.cars.extract.Extractable;

@Getter
@Setter
@Builder
@ToString
public class BrandExtractDto extends Extractable {

	private String name;
	private String url;
	private List<ModelExtractDto> models;
	
}
