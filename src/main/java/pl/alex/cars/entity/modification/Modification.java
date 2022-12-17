package pl.alex.cars.entity.modification;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Modification {
    private String modificationName;
    private String pictureLink;
    private List<Variant> variants;

    public Modification(String modificationName, String pictureLink) {
        this.modificationName = modificationName;
        this.pictureLink = pictureLink;
    }

    public void addVariant(Variant variant){
        if (variants==null){
            variants=new ArrayList<>();
        }
        variants.add(variant);
    }

    @Override
    public String toString() {
        return "Modification: " + modificationName +
                "Photo: " + pictureLink;
    }
}
