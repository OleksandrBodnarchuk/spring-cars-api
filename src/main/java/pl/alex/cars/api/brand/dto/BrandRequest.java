package pl.alex.cars.api.brand.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.utils.CarPageable;

@Getter
@Setter
public class BrandRequest extends CarPageable {
	private List<String> names;
}
