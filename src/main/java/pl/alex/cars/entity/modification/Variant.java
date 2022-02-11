package pl.alex.cars.entity.modification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.entity.chasis.Chassis;
import pl.alex.cars.entity.dimentions.Dimensions;
import pl.alex.cars.entity.engine.Engine;
import pl.alex.cars.entity.transmission.Transmission;

@Getter
@Setter
@Builder
public class Variant {
    private Bodywork bodywork;
    private Engine engine;
    private Transmission transmission;
    private Dimensions dimensions;
    private Chassis chassis;

    @Override
    public String toString() {
        return "Bodywork: " + bodywork + '\n' +
                "Engine: " + engine + '\n' +
                "Transmission: " + transmission + '\n' +
                "Dimensions: " + dimensions + '\n' +
                "Chassis: " + chassis + '\n' +
                '}';
    }

}
