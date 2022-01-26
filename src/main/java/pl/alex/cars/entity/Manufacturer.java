package pl.alex.cars.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.entity.model.Model;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Manufacturer {
    private String title;
    private String brandLogo;
    private final List<Model> models = new ArrayList<>();

    public void addModel(Model model) {
        this.models.add(model);
    }

    @Override
    public String toString() {
        return "BRAND--> {" + '\n' +
                "Title: " + title + '\n' +
                "Picture: " + brandLogo + '\n' +
                "Models: " + models + '\n' +
                "}";
    }
}
