package pl.alex.cars.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Model {
    private String title;
    private final List<Modification> modifications = new ArrayList<>();
    private String pictureLink;


    public void addModification(Modification modification) {
        this.modifications.add(modification);
    }

    @Override
    public String toString() {
        return "Model{" + '\n' +
                "Title: " + title + '\n' +
                "Picture Link: " + pictureLink + '\n' +
                "Modification: " + modifications + '\n' +
                '}';
    }
}
