package pl.alex.cars.entity.engine;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Engine {
    private String fuel;
    private String fuelSystem;

    private String engineType;
    private String enginePosition;
    private String engineCapacityCc;
    private String cylinders;
    private String valves;

    private String powerOutputBhp;
    private String powerOutputKw;
    private String powerOutputRpm;

    private String accelerationTo100;
    private String topSpeed;

    @Override
    public String toString() {
        return "Engine{\n" +
                "Fuel" + fuel + '\n' +
                "Fuel System" + fuelSystem + '\n' +
                "engineType='" + engineType + '\n' +
                "enginePosition='" + enginePosition + '\n' +
                "engineCapacityCc=" + engineCapacityCc + '\n' +
                "cylinders=" + cylinders + '\n' +
                "valves=" + valves + '\n' +
                "powerOutputBhp=" + powerOutputBhp + '\n' +
                "powerOutputKw=" + powerOutputKw + '\n' +
                "powerOutputRpm=" + powerOutputRpm + '\n' +
                "accelerationTo100=" + accelerationTo100 + '\n' +
                "topSpeed=" + topSpeed + '\n' +
                '}';
    }
}
