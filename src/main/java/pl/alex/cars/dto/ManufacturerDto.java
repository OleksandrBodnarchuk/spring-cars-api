package pl.alex.cars.dto;

import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.entity.Logo;

@Getter
@Setter
public class ManufacturerDto {
	private long id;
	private String name;
	private Long value;
}
