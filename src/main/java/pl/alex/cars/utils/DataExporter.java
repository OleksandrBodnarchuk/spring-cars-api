package pl.alex.cars.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.alex.cars.entity.Manufacturer;
import pl.alex.cars.entity.Model;
import pl.alex.cars.entity.Modification;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

public class DataExporter {

    public static final String MAIN_URL = "http://www.automobile-data.com/";

    private static final List<Manufacturer> manufacturers = new ArrayList<>();


    public static void gatherData() throws IOException {
        System.out.println("Loading data from URL...");
        Document document;
        try {
            document = Jsoup.connect(MAIN_URL).timeout(10 * 10000).get();
        } catch (ConnectException e) {
            document = Jsoup.connect(MAIN_URL).timeout(10 * 10000).get();
        }

        Elements brandElements = document.getElementsByClass("mods-makes");
        // GETTING BRANDS
        getBrands(brandElements);
        System.out.println(manufacturers);
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
        Document models;
        try {
            models = Jsoup.connect(MAIN_URL + brandElements.get(i).attr("href")).timeout(10 * 10000).get();
        } catch (ConnectException e) {
            models = Jsoup.connect(MAIN_URL + brandElements.get(i).attr("href")).timeout(10 * 10000).get();

        }
        Elements modelElements = models.getElementsByClass("mods-makes mods-models");
        for (Element modelElement : modelElements) {

            Model model = new Model();
            model.setModel(modelElement.attr("title"));
            model.setPictureLink(MAIN_URL + modelElement.getElementsByAttribute("src").attr("src"));

            String modelLink = MAIN_URL + modelElement.attr("href");
            Document modificationsDoc;
            try {
                modificationsDoc = Jsoup.connect(modelLink).timeout(10 * 50000).get();
            } catch (ConnectException e) {
                modificationsDoc = Jsoup.connect(modelLink).timeout(10 * 50000).get();
            }

            Elements modificationElements = modificationsDoc.getElementsByClass("mods-container");
            Modification modification = new Modification();

            // GETTING MODEL MODIFICATIONS
            getModelModifications(manufacturer, model, modificationElements, modification);
        }
    }

    private static void getModelModifications(Manufacturer manufacturer, Model model, Elements modificationElements, Modification modification) {
        for (Element modificationElement : modificationElements) {
            Elements modelModificationDetails = modificationElement.getElementsByAttributeValue("class", "fl");
            Elements modelModificationPhoto = modificationElement.getElementsByClass("modification-group-photo");

            if (modelModificationDetails.size() > 0) {
                getModelModificationData(modification, modelModificationDetails, modelModificationPhoto, model);
            }
            manufacturer.addModel(model);
        }
        manufacturers.add(manufacturer);
    }

    private static void getModelModificationData(Modification modification, Elements modelModifications, Elements modelModificationPhoto, Model model) {
        String modelTitle;
        String modificationPhoto;
        for (int i = 0; i < modelModificationPhoto.size(); i++) {
            modelTitle = modelModifications.get(i).getElementsByTag("span").first().text();
            modificationPhoto = MAIN_URL + modelModificationPhoto.get(i).getElementsByAttribute("src").attr("src");
            modification.setModification(modelTitle);
            modification.setPictureLink(modificationPhoto);
            model.addModification(modification);
            System.out.println(modification);
        }
    }
}
