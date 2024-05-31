package pl.alex.cars.api.brand.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.api.model.dto.ModelResponse;

@Getter
@Setter
@JsonInclude(value = Include.NON_DEFAULT)
public class BrandResponse {
	private String name;
	private List<BrandResponse> brandNames;
	private List<ModelResponse> models;
}
