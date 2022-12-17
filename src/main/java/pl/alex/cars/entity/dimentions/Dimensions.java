package pl.alex.cars.entity.dimentions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Dimensions {
    private String length;
    private String width;
    private String height;
    private String weight;
    private String wheelBase;

    @Override
    public String toString() {
        return "Dimensions{\n" +
                "lengthMm: " + length + "\n" +
                "widthMm: " + width + "\n" +
                "heightMm: " + height + "\n" +
                "weight: " + weight + "\n" +
                "wheelBaseMm: " + wheelBase + "\n" +
                '}';
    }
}
