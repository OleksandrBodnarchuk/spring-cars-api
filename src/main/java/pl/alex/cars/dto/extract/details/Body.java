package pl.alex.cars.dto.extract.details;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Body {
    private String length;
    private String width;
    private String height;
    private String wheelBase;
    private String weight;
    private String maxWidth;
    private String bootCapacity;
    

}
