package pl.alex.cars.entity.fuelConsumption;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FuelConsumption {
    private String urban;
    private String extraUrban;
    private String combined;
    private String cO2Emissions;
    private String fuelTankCapacity;

    @Override
    public String toString() {
        return "FuelConsumption{\n" +
                "Urban='" + urban + '\n' +
                ", ExtraUrban='" + extraUrban + '\n' +
                ", Combined='" + combined + '\n' +
                ", CO2Emissions='" + cO2Emissions + '\n' +
                ", fuelTankCapacity='" + fuelTankCapacity + '\n' +
                '}';
    }
}
