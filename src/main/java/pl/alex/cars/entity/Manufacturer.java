package pl.alex.cars.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Manufacturer {
    private String title;
    private String brandLogo;
    private final List<Model> models = new ArrayList<>();

    public void addModel(Model model) {
        this.models.add(model);
    }

    @Override
    public String toString() {
        return "Car {" + '\n' +
                "Title: " + title + '\n' +
                "Picture: " + brandLogo + '\n' +
                "Models: " + models + '\n' +
                "}";
    }
}
