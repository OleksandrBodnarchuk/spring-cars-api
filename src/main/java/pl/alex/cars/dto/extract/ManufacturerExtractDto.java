package pl.alex.cars.dto.extract;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ManufacturerExtractDto extends Extractable {

	private String name;
	private String url;
	private List<ModelExtractDto> models;
	
}
