package pl.alex.cars.entity.transmission;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Transmission {
    private String driveWheels;
    private String steering;
    private String gearbox;

    @Override
    public String toString() {
        return "Transmission{\n" +
                "driveWheels " + driveWheels + "\n" +
                "steering " + steering + "\n" +
                "gearbox " + gearbox + "\n" +
                '}';
    }
}
