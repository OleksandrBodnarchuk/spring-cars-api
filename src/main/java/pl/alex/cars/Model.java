package pl.alex.cars;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Model {
    private final List<Modification> modifications = new ArrayList<>();
    private String pictureLink;


    public void addModification(Modification modification) {
        this.modifications.add(modification);
    }

    @Override
    public String toString() {
        return "Model{" + '\n' +
                "pictureLink='" + pictureLink + '\n' +
                "Modification" + modifications + '\n' +
                '}';
    }
}
