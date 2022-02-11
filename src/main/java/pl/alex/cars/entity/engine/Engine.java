package pl.alex.cars.entity.engine;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Engine {
    private String engineName;
    private String fuel;
    private String fuelSystem;

    private String engineType;
    private String enginePosition;
    private int engineCapacityCc;
    private String cylinders_valves;

    private int powerOutputBhp;
    private int powerOutputKw;
    private int powerOutputRpm;

    private int torqueNm;
    private int torqueRpm;

    private float accelerationTo100;
    private int topSpeed;


}
