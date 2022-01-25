package pl.alex.cars;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.alex.cars.entity.Manufacturer;
import pl.alex.cars.entity.Model;
import pl.alex.cars.entity.Modification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataExporter {

    public static final String MAIN_URL = "http://www.automobile-data.com/";

    private static final List<Manufacturer> manufacturers = new ArrayList<>();


    public static void gatherData() throws IOException {
        System.out.println("Connecting to main URL...");
        Document document = Jsoup.connect(MAIN_URL).timeout(10 * 10000).get();
        Elements brandElements = document.getElementsByClass("mods-makes");

        // GETTING BRANDS
        getBrands(brandElements);
    }

    private static void getBrands(Elements brandElements) throws IOException {
        for (int i = 0; i < brandElements.size() - 1; i++) {
            Manufacturer manufacturer = new Manufacturer();
            String brandName = brandElements.get(i).attr("title");
            manufacturer.setTitle(brandName);
            String brandLogo = brandElements.get(i).getElementsByAttribute("src").attr("src");
            manufacturer.setBrandLogo(brandLogo);

            // GETTING MODELS
            getModels(brandElements, i, manufacturer);
        }
    }

    private static void getModels(Elements brandElements, int i, Manufacturer manufacturer) throws IOException {
        Document models = Jsoup.connect(MAIN_URL + brandElements.get(i).attr("href")).get();
        Elements modelElements = models.getElementsByClass("mods-makes mods-models");
        for (Element modelElement : modelElements) {

            Model model = new Model();
            model.setTitle(modelElement.attr("title"));
            model.setPictureLink(MAIN_URL + modelElement.getElementsByAttribute("src").attr("src"));

            String modelLink = MAIN_URL + modelElement.attr("href");

            Document modificationsDoc = Jsoup.connect(modelLink).get();
            Elements modificationElements = modificationsDoc.getElementsByAttributeValue("name", "modifications");
            Modification modification = new Modification();

            // GETTING MODEL MODIFICATIONS
            getModelModifications(manufacturer, model, modificationElements, modification);
        }
    }

    private static void getModelModifications(Manufacturer manufacturer, Model model, Elements modificationElements, Modification modification) {
        for (Element modificationElement : modificationElements) {
            Elements modelModifications = modificationElement.getElementsByClass("modification-group-name");
            Elements modelModificationPhoto = modificationElement.getElementsByClass("modification-group-photo");

            getModelModificationData(modification, modelModifications, modelModificationPhoto);

            model.addModification(modification);
            manufacturer.addModel(model);
            System.out.println(manufacturer);

        }
    }

    private static void getModelModificationData(Modification modification, Elements modelModifications, Elements modelModificationPhoto) {
        for (int j = 0; j < modelModifications.size(); j++) {
            String name = modelModifications.get(j).getElementsByTag("span").text();
            modification.setName(name);
            String modificationPicture = MAIN_URL + modelModificationPhoto.get(j).getElementsByTag("img").attr("src");
            modification.setPictureLink(modificationPicture);
        }
    }
}
