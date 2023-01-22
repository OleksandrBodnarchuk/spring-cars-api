package pl.alex.cars.car.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.utils.ResponseDto;

@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
public class ModelResponse extends ResponseDto {

}
