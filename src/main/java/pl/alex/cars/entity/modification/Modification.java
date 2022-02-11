package pl.alex.cars.entity.modification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.entity.engine.Engine;

import java.util.List;

@Getter
@Setter
@Builder
public class Modification {
    private String modificationName;
    private String pictureLink;

    @Override
    public String toString() {
        return "Modification: " + modificationName +
                "Photo: " + pictureLink;
    }
}
