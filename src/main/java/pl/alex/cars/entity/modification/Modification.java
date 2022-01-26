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
    private String modification;
    private String pictureLink;
    private List<Engine> engine;


    @Override
    public String toString() {
        return "Modification: " + modification + '\n' +
                "Picture: " + pictureLink + '\n' +
                "Engines: " + engine + '\n';

    }
}
