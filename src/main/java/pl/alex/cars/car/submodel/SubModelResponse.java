package pl.alex.cars.car.submodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import pl.alex.cars.utils.ResponseDto;

@JsonInclude(value = Include.NON_NULL)
public class SubModelResponse extends ResponseDto {

}
