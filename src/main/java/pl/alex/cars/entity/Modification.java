package pl.alex.cars.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Modification {
    private String Modification;
    private String pictureLink;
    private List<Engine> engine;


    @Override
    public String toString() {
        return "Modification: " + Modification + '\n' +
                "Picture: " + pictureLink + '\n' +
                "Engines: " + engine + '\n';

    }
}
