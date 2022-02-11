package pl.alex.cars.entity.modification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Variant {
    private Bodywork bodywork;

    @Override
    public String toString() {
        return "Bodywork: " + bodywork + '\n' +
                '}';
    }

}
