package pl.alex.cars.dto.extract;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VariantExtractDto {

	private String name;
	private String url;
	private String imageUrl;
	private List<ModificationExctractDto> modifications;
}
