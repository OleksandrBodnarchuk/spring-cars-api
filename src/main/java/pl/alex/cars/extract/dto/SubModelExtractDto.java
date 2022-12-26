package pl.alex.cars.extract.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubModelExtractDto {

	private String name;
	private String url;
	private List<ModificationExctractDto> modifications;
}
