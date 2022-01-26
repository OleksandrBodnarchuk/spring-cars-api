package pl.alex.cars.entity.model;

import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.entity.modification.Modification;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Model {
    private String model;
    private final List<Modification> modifications = new ArrayList<>();



    public void addModification(Modification modification) {
        this.modifications.add(modification);
    }

    @Override
    public String toString() {
        return "Model: " + model + '\n' +
                "Modifications: " + modifications + '\n' +
                '}';
    }
}
