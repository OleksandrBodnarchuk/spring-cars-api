package pl.alex.cars.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = Include.NON_EMPTY)
public class PageResponseDto {

	protected int ordinal;
	protected String name;
}
