package pl.alex.cars.utils;

import lombok.Getter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.alex.cars.entity.Manufacturer;
import pl.alex.cars.entity.engine.Engine;
import pl.alex.cars.entity.model.Model;
import pl.alex.cars.entity.modification.Modification;

import java.io.IOException;
import java.util.List;

/*
 * Class that helps to extract data for Modifications
 */
@Getter
public class ModificationUtils {
    private static List<Engine> engines;

    protected static void getModelModifications(Manufacturer manufacturer, Model model, String modelModificationsUrl) throws IOException {
        Document modificationsDoc = ConnectionUtil.getHtmlDocFromUrl(modelModificationsUrl);
        Elements modificationElements = modificationsDoc.getElementsByClass("mods-container");

        for (Element modificationElement : modificationElements) {
            Elements modelModificationDetails = modificationElement.getElementsByAttributeValue("class", "fl");
            Elements modelModificationPhoto = modificationElement.getElementsByClass("modification-group-photo");

            if (modelModificationDetails.size() > 0) {
                ModificationUtils.getModificationData(modelModificationDetails, modelModificationPhoto, model);
            }
            manufacturer.addModel(model);
        }
    }

    private static void getModificationData(Elements modifications, Elements modelModificationPhoto, Model model) {
        Modification modification;
        String modificationName;
        String modificationPhoto;

        for (int i = 0; i < modelModificationPhoto.size(); i++) {
            // model modification data export
            modificationName = modifications.get(i).getElementsByTag("span").first().text();
            modificationPhoto = extractModificationPhoto(modelModificationPhoto, i);

            // ENGINE DATA EXPORT
            engines = EngineUtils.engineDataMapper(modifications, i);

            // Modification build
            modification = Modification.builder()
                    .modification(modificationName)
                    .engine(engines)
                    .pictureLink(modificationPhoto)
                    .build();

            model.addModification(modification);
        }
    }

    private static String extractModificationPhoto(Elements modelModificationPhoto, int i) {
        return ConnectionUtil.MAIN_URL + modelModificationPhoto.get(i).getElementsByAttribute("src").attr("src");
    }

}
