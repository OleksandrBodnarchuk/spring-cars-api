package pl.alex.cars.utils;

import lombok.Getter;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.alex.cars.entity.Manufacturer;
import pl.alex.cars.entity.model.Model;
import pl.alex.cars.entity.modification.Modification;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Class that helps to extract data for Modifications
 */
@Getter
public class ModificationUtils {

    protected static void getModelModifications(Manufacturer manufacturer, Model model, String modelModificationsUrl) throws IOException {
        Modification modification = null;
        modelModificationsUrl = "http://www.automobile-data.com/?model_id=151"; // TODO: DELETE
        Document modificationsDoc = ConnectionUtil.getHtmlDocFromUrl(modelModificationsUrl);
        Elements modificationElements = modificationsDoc.getElementsByClass("fl");
        Elements modelModificationPhoto = modificationsDoc.getElementsByAttributeValueStarting("src", "/images/modification_groups/");

        for (int i = 0; i < modelModificationPhoto.size(); i++) {
            String modificationName = modificationElements.get(i + 1).getElementsByClass("modification-group-name").text();
            String modificationPhoto = extractModificationPhoto(modelModificationPhoto, i);
            // getting urls to each modification
            List<String> modificationVariantsLinks = getModificationVariantsLinks(modificationElements, i);

            modification = Modification.builder()
                    .modificationName(modificationName)
                    .pictureLink(modificationPhoto)
                    .build();
        }
        manufacturer.addModel(model);
    }

    private static List<String> getModificationVariantsLinks(Elements modificationElements, int i) {
        return modificationElements
                .get(i + 1)
                .getElementsByAttributeValueStarting("href", "?modification_id=")
                .stream()
                .map(element -> ConnectionUtil.MAIN_URL + element.getElementsByAttribute("href").attr("href"))
                .distinct().toList();
    }

    private static String extractModificationPhoto(Elements modelModificationPhoto, int i) {
        return ConnectionUtil.MAIN_URL + modelModificationPhoto.get(i).getElementsByAttribute("src").attr("src");
    }

}
