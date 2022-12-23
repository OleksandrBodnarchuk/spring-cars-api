package pl.alex.cars.dto.extract;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ModificationExctractDto {
	private String name;
	private String url;
	private String mainImage;
	
}
