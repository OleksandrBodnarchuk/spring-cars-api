package pl.alex.cars.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CarPageable {
	private Integer pageNumber;
	private Integer pageSize;

	@JsonIgnore
	public Integer getOrdinal() {
		return pageSize * pageNumber + 1;
	}
}
