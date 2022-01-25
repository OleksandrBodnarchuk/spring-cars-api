package pl.alex.cars.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Engine {
    private String type;
    private String power;
    private String fuel;

    @Override
    public String toString() {
        return "Engine: " + '\n' +
                "Type: " + type + '\n' +
                "Power: " + power + '\n' +
                "Fuel: " + fuel + '\n';
    }
}
