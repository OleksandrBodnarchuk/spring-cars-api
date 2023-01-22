package pl.alex.cars.car.brand;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.car.model.ModelResponse;

@Getter
@Setter
@JsonInclude(value = Include.NON_DEFAULT)
public class BrandResponse {
	private int ordinal;
	private String name;
	private List<BrandResponse> brandNames;
	private List<ModelResponse> models;
}
