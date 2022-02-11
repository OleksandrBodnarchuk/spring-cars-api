package pl.alex.cars.entity.modification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.entity.engine.Engine;
import pl.alex.cars.entity.transmission.Transmission;

@Getter
@Setter
@Builder
public class Variant {
    private Bodywork bodywork;
    private Engine engine;
    private Transmission transmission;

    @Override
    public String toString() {
        return "Bodywork: " + bodywork + '\n' +
                "Engine: " + engine + '\n' +
                "Transmission: " + transmission + '\n' +
                '}';
    }

}
