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
        List<String> engineData;
        List<Engine> engines = new ArrayList<>();
        for (int i = 0; i < modelModificationPhoto.size(); i++) {
            // model modification data export
            modelTitle = modelModifications.get(i).getElementsByTag("span").first().text();
            modificationPhoto = MAIN_URL + modelModificationPhoto.get(i).getElementsByAttribute("src").attr("src");

            // Engine data export
            engineData = modelModifications.get(i).getElementsByAttributeValueStarting("href", "?modification_id=").eachText();
            engineDataMapper(engineData, engines);

            // Modification build
            modification = buildModification(modelTitle, modificationPhoto, engines);
            model.addModification(modification);

            System.out.println(model);
        }
    }

    private static void engineDataMapper(List<String> engineData, List<Engine> engines) {
        Engine engine;

        for (int j = 0; j < engineData.size(); j=j+5) {
            engine = new Engine();
            engine.setType(engineData.get(j));
            engine.setPower(engineData.get(j + 1));
            engine.setFuel(engineData.get(j + 2));
            engine.setBody(engineData.get(j + 3));
            engine.setYearOfProduction(engineData.get(j + 4));
            engines.add(engine);
        }
    }

    private static Modification buildModification(String modelTitle, String modificationPhoto, List<Engine> engines) {
        Modification modification;
        modification = new Modification();
        modification.setEngine(engines);
        modification.setModification(modelTitle);
        modification.setPictureLink(modificationPhoto);
        return modification;
    }


}
