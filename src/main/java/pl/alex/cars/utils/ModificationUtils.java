package pl.alex.cars.utils;

import lombok.Getter;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.alex.cars.entity.Manufacturer;
import pl.alex.cars.entity.engine.Engine;
import pl.alex.cars.entity.model.Model;

import java.io.IOException;
import java.util.List;

/*
 * Class that helps to extract data for Modifications
 */
@Getter
public class ModificationUtils {

    protected static void getModelModifications(Manufacturer manufacturer, Model model, String modelModificationsUrl) throws IOException {
       modelModificationsUrl = "http://www.automobile-data.com/?model_id=151"; // TODO: DELETE
        Document modificationsDoc = ConnectionUtil.getHtmlDocFromUrl(modelModificationsUrl);
        Elements modificationElements = modificationsDoc.getElementsByClass("fl");
        Elements modelModificationPhoto = modificationsDoc.getElementsByAttributeValueStarting("src","/images/modification_groups/");

        getModificationPhoto(modelModificationPhoto);
        manufacturer.addModel(model);
        }

    private static String getModificationPhoto(Elements modelModificationPhoto) {
        String modificationPhotoUrl = "";
        for (int i = 0; i < modelModificationPhoto.size(); i++) {
            // Getting photo for each modification
            modificationPhotoUrl = modelModificationPhoto.get(i).attr("src");
        }
        return modificationPhotoUrl;
    }


    private static String extractModificationPhoto(Elements modelModificationPhoto, int i) {
        return ConnectionUtil.MAIN_URL + modelModificationPhoto.get(i).getElementsByAttribute("src").attr("src");
    }

}
