package pl.alex.cars.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.alex.cars.entity.Engine;
import pl.alex.cars.entity.Manufacturer;
import pl.alex.cars.entity.Model;
import pl.alex.cars.entity.Modification;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

            String modelLink = MAIN_URL + modelElement.attr("href");
            Document modificationsDoc;
            try {
                modificationsDoc = Jsoup.connect(modelLink).timeout(10 * 50000).get();
            } catch (ConnectException e) {
                modificationsDoc = Jsoup.connect(modelLink).timeout(10 * 50000).get();
            }

            Elements modificationElements = modificationsDoc.getElementsByClass("mods-container");

            // GETTING MODEL MODIFICATIONS
            getModelModifications(manufacturer, model, modificationElements);
        }
    }

    private static void getModelModifications(Manufacturer manufacturer, Model model, Elements modificationElements) {
        for (Element modificationElement : modificationElements) {
            Elements modelModificationDetails = modificationElement.getElementsByAttributeValue("class", "fl");
            Elements modelModificationPhoto = modificationElement.getElementsByClass("modification-group-photo");

            if (modelModificationDetails.size() > 0) {
                getModelModificationData(modelModificationDetails, modelModificationPhoto, model);
            }
            manufacturer.addModel(model);
        }
        manufacturers.add(manufacturer);
    }

    private static void getModelModificationData(Elements modelModifications, Elements modelModificationPhoto, Model model) {
        Modification modification;
        String modelTitle;
        String modificationPhoto;
        List<String> fuelType;
        List<String> engineTypes;
        List<Engine> engines = new ArrayList<>();
        for (int i = 0; i < modelModificationPhoto.size(); i++) {
            modelTitle = modelModifications.get(i).getElementsByTag("span").first().text();
            modificationPhoto = MAIN_URL + modelModificationPhoto.get(i).getElementsByAttribute("src").attr("src");

            engineTypes = modelModifications.get(i).getElementsByClass("tl modification-table-title").eachText();
            fuelType = modelModifications.get(i).getElementsByClass("fuel").eachText();

            Engine engine;
            for (int j = 0; j < engineTypes.size(); j++) {
                engine = new Engine();
                engine.setFuel(fuelType.get(j));
                engine.setType(engineTypes.get(j));
                engines.add(engine);
            }

            modification = new Modification();
            modification.setEngine(engines);
            modification.setModification(modelTitle);
            modification.setPictureLink(modificationPhoto);

            model.addModification(modification);

            System.out.println(model);
        }
    }


}
