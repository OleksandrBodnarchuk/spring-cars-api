package pl.alex.cars.entity.dimentions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Dimensions {
    private int lengthMm;
    private int widthMm;
    private int heightMm;
    private int weight;
    private int maxWeight;
    private int wheelBaseMm;

    @Override
    public String toString() {
        return "Dimensions{\n" +
                "lengthMm: " + lengthMm + "\n" +
                "widthMm: " + widthMm + "\n" +
                "heightMm: " + heightMm + "\n" +
                "weight: " + weight + "\n" +
                "maxWeight: " + maxWeight + "\n" +
                "wheelBaseMm: " + wheelBaseMm + "\n" +
                '}';
    }
}
