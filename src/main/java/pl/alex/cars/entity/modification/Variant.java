package pl.alex.cars.entity.modification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.entity.engine.Engine;

@Getter
@Setter
@Builder
public class Variant {
    private Bodywork bodywork;
    private Engine engine;

    @Override
    public String toString() {
        return "Bodywork: " + bodywork + '\n' +
                "Engine: " + engine + '\n' +
                '}';
    }

}
