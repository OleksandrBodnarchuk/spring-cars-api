package pl.alex.cars.api.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.utils.CarPageable;

@Getter
@Setter
public class ModelRequest extends CarPageable {
	private List<String> names;

}
