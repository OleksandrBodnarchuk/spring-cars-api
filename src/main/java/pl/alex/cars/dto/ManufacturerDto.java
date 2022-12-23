package pl.alex.cars.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManufacturerDto {
	private long id;
	private String name;
	private Long value;
	private List<ModelDto> models;
}
