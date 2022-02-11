package pl.alex.cars.entity.chasis;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Chassis {

    private String frontSuspension;
    private String rearSuspension;
    private String frontBrakes;
    private String rearBrakes;
    private String frontTires;
    private String rearTires;

    @Override
    public String toString() {
        return "Chassis{\n" +
                "frontSuspension='" + frontSuspension + '\n' +
                ", rearSuspension='" + rearSuspension + '\n' +
                ", frontBrakes='" + frontBrakes + '\n' +
                ", rearBrakes='" + rearBrakes + '\n' +
                ", frontTires='" + frontTires + '\n' +
                ", rearTires='" + rearTires + '\n' +
                '}';
    }
}
