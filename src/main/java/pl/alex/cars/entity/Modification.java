package pl.alex.cars.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Modification {
    private String name;
    private String pictureLink;
    private String engine;
    private String power;
    private String fuel;
    private String body;
    private String yearOfProduction;

    @Override
    public String toString() {
        return "Modification: {" + '\n' +
                "Name: " + name + '\n' +
                "Picture: " + pictureLink + '\n' +
                "Production years: " + yearOfProduction + '\n' +
                "Engine " + engine + '\n' +
                "Power " + power + '\n' +
                "Fuel " + fuel + '\n' +
                "Body " + body + '\n' +
                "}";
    }
}
