package pl.alex.cars.dto.extract.details;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EngineExtractDto {
    private String displacement;
    private String kw;
    private String hp;
    private String torque;
    private String fuelSupply;
    private String cylinders;
    private String cylinderDiameter;
    private String ValvesInCylinders;
    private String gears;
    private String fuel;
    private String fuelCapacity;
    private String ecoStandart;

}
