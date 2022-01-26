package pl.alex.cars.entity.engine;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Engine {
    private String type;
    private String power;
    private String fuel;
    private String body;
    private String yearOfProduction;

    @Override
    public String toString() {
        return "Engine: " + '\n' +
                "Type: " + type + '\n' +
                "Power: " + power + '\n' +
                "Fuel: " + fuel + '\n' +
                "Body " + body + '\n' +
                "Production years: " + yearOfProduction + '\n';
    }
}
