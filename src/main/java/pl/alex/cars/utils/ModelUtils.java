package pl.alex.cars.utils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.alex.cars.entity.Manufacturer;
import pl.alex.cars.entity.model.Model;

import java.io.IOException;

public class ModelUtils {
    protected static void getModels(Elements brandElements, int i, Manufacturer manufacturer) throws IOException {
        String modelsUrl = ConnectionUtil.MAIN_URL + brandElements.get(i).attr("href");

        Document models = ConnectionUtil.getHtmlDocFromUrl(modelsUrl);
        Elements modelElements = models.getElementsByClass("mods-makes mods-models");
        for (Element modelElement : modelElements) {
            Model model = new Model();
            model.setModel(modelElement.attr("title"));
            // GETTING MODIFICATIONS - PER MODEL
            String modelModificationsUrl = ConnectionUtil.MAIN_URL + modelElement.attr("href");
            ModificationUtils.getModelModifications(manufacturer, model, modelModificationsUrl);
        }
    }
}
