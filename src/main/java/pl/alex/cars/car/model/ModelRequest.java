package pl.alex.cars.car.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.utils.CarPageable;

@Getter
@Setter
class ModelRequest extends CarPageable {
	private List<String> names;

}
