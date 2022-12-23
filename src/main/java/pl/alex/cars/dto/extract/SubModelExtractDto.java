package pl.alex.cars.dto.extract;

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
	private String imageLink;
	private List<ModificationExctractDto> modifications;
}
